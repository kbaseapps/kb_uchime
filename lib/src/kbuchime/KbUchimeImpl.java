package kbuchime;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.util.zip.*;
import java.net.URL;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.*;
import us.kbase.workspace.*;
import us.kbase.common.utils.FastaWriter;

import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;

import setapi.*;
import kbasereport.*;
import readsutils.*;

import org.strbio.IO;
import org.strbio.io.*;
import org.strbio.util.*;
import com.fasterxml.jackson.databind.*;

import static java.lang.ProcessBuilder.Redirect;

/**
   This class runs UCHIME (in public domain version of UCHIME, or the
   re-implemented version in VSEARCH) against a single read library
   or set of reads
*/
public class KbUchimeImpl {
    protected static java.io.File tempDir = new java.io.File("/kb/module/work/tmp/");

    /**
       creates a workspace client; if token is null, client can
       only read public workspaces.
    */
    public static WorkspaceClient createWsClient(String wsURL,
                                                 AuthToken token) throws Exception {
        WorkspaceClient rv = null;

        if (token==null)
            rv = new WorkspaceClient(new URL(wsURL));
        else
            rv = new WorkspaceClient(new URL(wsURL),token);
        rv.setAuthAllowedForHttp(true);
        return rv;
    }

    /**
       look up the type of an object
    */
    public static String lookupWsType(WorkspaceClient wc,
                                      String ref) throws Exception {
        
        return wc.getObjectInfoNew(new GetObjectInfoNewParams()
                                   .withObjects(Arrays.asList(new ObjectIdentity().withRef(ref)))).get(0).getE3();
    }

    /**
       Make and save Report object, returning its name and reference
    */
    public static String[] makeReport(AuthToken token,
                                      String ws,
                                      String reportText,
                                      List<String> warnings,
                                      List<WorkspaceObject> objects) throws Exception {
        Report report = new Report()
            .withTextMessage(reportText)
            .withWarnings(warnings)
            .withObjectsCreated(objects);

        KBaseReportClient rc = new KBaseReportClient(new URL(System.getenv("SDK_CALLBACK_URL")),token);
        rc.setIsInsecureHttpConnectionAllowed(true);
        ReportInfo ri = rc.create(new CreateParams()
                                  .withReport(report)
                                  .withWorkspaceName(ws));
        
        return new String[] { ri.getName(), ri.getRef() };
    }
    

    /**
       Runs UCHIME on a single reads library or set.
    */
    public static RunUchimeOutput runUchime(String wsURL,
                                            String serviceWizardURL,
                                            AuthToken token,
                                            RunUchimeInput input) throws Exception {

        WorkspaceClient wc = createWsClient(wsURL,token);
        ReadsUtilsClient ruc = new ReadsUtilsClient(new URL(System.getenv("SDK_CALLBACK_URL")),token);
        ruc.setIsInsecureHttpConnectionAllowed(true);

        String readsRef = input.getInputReadsName();
        if (readsRef.indexOf("/") == -1)
            readsRef = input.getWs()+"/"+readsRef;

        // use old UCHIME, or VSEARCH version?
        boolean oldUCHIME = input.getProgramName().equals("UCHIME");

        // figure out what type of reads we have
        String readsType = lookupWsType(wc, readsRef);
        System.out.println("Reads type is "+readsType);

        // list of reads to work on        
        ArrayList<String> readsRefs = new ArrayList<String>();
        // and input names
        ArrayList<String> readsNames = new ArrayList<String>();
        // and output names
        ArrayList<String> outputReadsNames = new ArrayList<String>();

        // if a set, get list of members
        if (readsType.equals("KBaseSets.ReadsSet")) {
            SetAPIClient sc = new SetAPIClient(new URL(serviceWizardURL),token);
            sc.setIsInsecureHttpConnectionAllowed(true);
            
            GetReadsSetV1Result readSetObj = sc.getReadsSetV1(new GetReadsSetV1Params().withRef(readsRef).withIncludeItemInfo(1L));
            for (ReadsSetItem rsi : readSetObj.getData().getItems()) {
                readsRefs.add(rsi.getRef());
                readsNames.add(rsi.getInfo().getE2());
                outputReadsNames.add(rsi.getInfo().getE2()+"_uchime");
            }
        }
        else {
            // process a single reads object, not a set
            readsRefs.add(readsRef);
            readsNames.add(input.getInputReadsName());
            outputReadsNames.add(input.getOutputReadsName());
        }

        // start generating report
        String reportText = "Filtering out chimeric reads using "+(oldUCHIME ? "public domain UCHIME 4.2.40" : "UCHIME implementation in VSEARCH 2.3.0");
        List<WorkspaceObject> objects = new ArrayList<WorkspaceObject>();
        List<String> warnings = null;

        // run UCHIME on each library
        for (int i=0; i<readsRefs.size(); i++) {
            readsRef = readsRefs.get(i);
            String readsName = readsNames.get(i);
            String outputReadsName = outputReadsNames.get(i);

            reportText += "Filtering out chimeric reads in "+readsName+" ("+readsRef+")\n";

            // download reads
            DownloadReadsOutput dro = ruc.downloadReads(new DownloadReadsParams()
                                                        .withReadLibraries(Arrays.asList(readsRef)));
            for (DownloadedReadLibrary drl : dro.getFiles().values()) {
                ReadsFiles rf = drl.getFiles();
                String fwdPath = rf.getFwd();
                String revPath = rf.getRev();
                if (fwdPath != null) {
                    java.io.File f = new java.io.File(fwdPath);
                    if ((f==null) || (f.length() == 0L)) {
                        reportText += "\nERROR: "+readsName+" did not load correctly or may have no reads\n";
                        if (warnings == null)
                            warnings = new ArrayList<String>();
                        warnings.add("ERROR: "+readsName+" did not load correctly or may have no reads");
                        continue;
                    }
                }
                if (revPath!=null) {
                    reportText += "\nWARNING: UCHIME doesn't work on paired end reads; they must be merged first.\n";
                    if (warnings == null)
                        warnings = new ArrayList<String>();
                    warnings.add("WARNING: UCHIME doesn't work on paired end reads; they must be merged first.");
                    java.io.File f = new java.io.File(revPath);
                    f.delete();
                }

                // write outputs to temp files
                java.io.File inputFileDerep = java.io.File.createTempFile("reads", ".fastq", tempDir);
                java.io.File inputFileDerepFA = java.io.File.createTempFile("reads", ".fa", tempDir);
                java.io.File inputFileFA = java.io.File.createTempFile("reads", ".fa", tempDir);
                java.io.File outputFile = java.io.File.createTempFile("output", ".txt", tempDir);
                java.io.File outputFileIDs = java.io.File.createTempFile("ids", ".txt", tempDir);
                java.io.File outputFileDerepFQ = java.io.File.createTempFile("reads", ".fastq", tempDir);
                java.io.File outputFileFQ = java.io.File.createTempFile("reads", ".fastq", tempDir);
                java.io.File outputFileLog = java.io.File.createTempFile("log", ".txt", tempDir);
                inputFileDerep.delete();
                inputFileDerepFA.delete();
                inputFileFA.delete();
                outputFile.delete();
                outputFileIDs.delete();
                outputFileDerepFQ.delete();
                outputFileFQ.delete();
                outputFileLog.delete();

                // see if any abundance data are already present
                ProcessBuilder pb = new ProcessBuilder("/bin/grep",
                                                       "-qE",
                                                       "';size=[[:digit:]]+;'",
                                                       fwdPath);
                int exitValue = pb.start().waitFor();
                boolean hasAbundanceData = (exitValue==0);

                // if no abundance data, make them
                if (hasAbundanceData) {
                    reportText += "\nAbundance data found.\n";
                    inputFileDerep = new java.io.File(fwdPath);
                }
                else {
                    reportText += "\nAbundance data not found; de-replicating and counting unique sequences using VSEARCH.\n";
                    pb = new ProcessBuilder("/kb/module/dependencies/bin/vsearch",
                                            "--derep_fulllength",
                                            fwdPath,
                                            "--sizeout",
                                            "--output",
                                            inputFileDerepFA.getPath());
                    pb.redirectErrorStream(true);
                    pb.redirectOutput(Redirect.to(outputFileLog));
                    pb.start().waitFor();

                    long fileSize = outputFileLog.length();
                    if (fileSize == 0L) {
                        reportText += "\nERROR: Failed to dereplicate and count unique sequences.\n";
                        if (warnings == null)
                            warnings = new ArrayList<String>();
                        warnings.add("ERROR: Failed to dereplicate and count unque sequences.");
                    }

                    // grab output into report
                    List<String> lines = Files.readAllLines(Paths.get(outputFileLog.getPath()), Charset.defaultCharset());
                    for (String line : lines)
                        reportText += line+"\n";

                    // put dereplicated data back in FQ
                    // build table of ids and sequences
                    pb = new ProcessBuilder("/bin/sh",
                                            "-c",
                                            "/bin/grep -e '^>' "+outputFile.getPath()+" | /usr/bin/cut -c 2- | sed -e 's/;size=\\([[:digit:]]\\+\\);/\t\\1/'");
                    pb.redirectOutput(Redirect.to(outputFileIDs));
                    pb.start().waitFor();

                    // load in counts
                    HashMap<String,Integer> counts = new HashMap<String,Integer>();
                    BufferedReader infile = IO.openReader(outputFileIDs.getPath());
                    String buffer = null;
                    while ((buffer = infile.readLine()) != null) {
                        String[] f = buffer.split("\t");
                        counts.put(f[0],new Integer(StringUtil.atoi(f[1])));
                    }
                    infile.close();

                    // add counts to FQ file
                    infile = IO.openReader(fwdPath);
                    PrintfWriter outfile = new PrintfWriter(inputFileDerep.getPath());
                    while ((buffer = infile.readLine()) != null) {
                        String header = buffer.substring(1);
                        Integer count = counts.get(header);
                        if (count == null) {
                            // skip this read
                            buffer = infile.readLine();
                            buffer = infile.readLine();
                            buffer = infile.readLine();
                        }
                        else {
                            // add count
                            outfile.printf("%s",buffer);
                            outfile.printf(";size=%d;\n",count.intValue());
                            buffer = infile.readLine();
                            outfile.printf("%s\n",buffer);
                            buffer = infile.readLine();
                            outfile.printf("%s\n",buffer);
                            buffer = infile.readLine();
                            outfile.printf("%s\n",buffer);
                        }
                    }
                    infile.close();
                    outfile.flush();
                    outfile.close();
                }

                // run UCHIME
                if (oldUCHIME) {
                    // run old version of UCHIME
                    // first, convert to FASTA and convert header size format
                    pb = new ProcessBuilder("/bin/sh",
                                            "-c",
                                            "/usr/bin/seqtk seq -A "+inputFileDerep.getPath()+" | sed -e 's/;size=\\([[:digit:]]\\+\\);/\\/ab=\\1\\//'");
                    pb.redirectOutput(Redirect.to(inputFileFA));
                    pb.start().waitFor();
                    
                    // then actually run uchime
                    pb = new ProcessBuilder("/kb/module/dependencies/bin/uchime",
                                            "--input",
                                            inputFileFA.getPath(),
                                            "--uchimeout",
                                            outputFile.getPath());
                    pb.redirectErrorStream(true);
                    pb.redirectOutput(Redirect.to(outputFileLog));
                    pb.start().waitFor();
                }
                else {
                    // run VSEARCH version
                    pb = new ProcessBuilder("/kb/module/dependencies/bin/vsearch",
                                            "--uchime_denovo",
                                            inputFileDerep.getPath(),
                                            "--nonchimeras",
                                            outputFile.getPath());
                    pb.redirectErrorStream(true);
                    pb.redirectOutput(Redirect.to(outputFileLog));
                    pb.start().waitFor();
                }

                // grab output into report
                List<String> lines = Files.readAllLines(Paths.get(outputFileLog.getPath()), Charset.defaultCharset());
                for (String line : lines) {
                    // should check for errors here
                    reportText += line+"\n";
                }

                // check that output actually generated
                long fileSize = outputFile.length();
                if (fileSize == 0L) {
                    reportText += "\nERROR: UCHIME did not generate any output.\n";
                    if (warnings == null)
                        warnings = new ArrayList<String>();
                    warnings.add("ERROR: UCHIME did not generate any output.");
                }
                else {
                    if (oldUCHIME) {
                        // get ids of nonchimeric seqs from tab file
                        // and convert to original count format
                        pb = new ProcessBuilder("/usr/bin/perl",
                                                "-ane",
                                                "$F[1]=~s/\\/ab=(\\d+)\\//;size=\\1;/; print \"$F[1]\n\" if ($F[16] eq \"N\")");
                        pb.redirectInput(Redirect.from(outputFile));
                        pb.redirectOutput(Redirect.to(outputFileIDs));
                        pb.start().waitFor();
                    }
                    else {
                        // get ids of nonchimeric seqs from fasta file
                        pb = new ProcessBuilder("/bin/sh",
                                                "-c",
                                                "/bin/grep -e '^>' "+outputFile.getPath()+" | /usr/bin/cut -c 2-"); // | sed -e 's/;size=\\([[:digit:]]\\+\\);/\/ab=\\1\//'");
                        pb.redirectOutput(Redirect.to(outputFileIDs));
                        pb.start().waitFor();
                    }

                    // extract subset of dereplicated fastq file
                    pb = new ProcessBuilder("/usr/bin/seqtk",
                                            "subseq",
                                            inputFileDerep.getPath(),
                                            outputFileIDs.getPath());
                    pb.redirectOutput(Redirect.to(outputFileDerepFQ));
                    pb.start().waitFor();

                    // convert input to FA, for search
                    pb = new ProcessBuilder("/usr/bin/seqtk",
                                            "seq",
                                            "-A",
                                            fwdPath);
                    pb.redirectOutput(Redirect.to(inputFileFA));
                    pb.start().waitFor();

                    // extract all sequences in original fastq file that match
                    pb = new ProcessBuilder("/kb/module/dependencies/bin/vsearch",
                                            "--search_exact",
                                            outputFileDerepFQ.getPath(),
                                            "--db",
                                            inputFileFA.getPath(),
                                            "--dbmask",
                                            "none",
                                            "--userfields",
                                            "target",
                                            "--userout",
                                            outputFileIDs.getPath());
                    pb.start().waitFor();

                    // and convert back to FQ again
                    pb = new ProcessBuilder("/usr/bin/seqtk",
                                            "subseq",
                                            fwdPath,
                                            outputFileIDs.getPath());
                    pb.redirectOutput(Redirect.to(outputFileFQ));
                    pb.start().waitFor();
                    
                    // save the output

                    // hack to fix missing sequencing tech,
                    // which is now mandatory but missing in some existing objects
                    String seqTech = drl.getSequencingTech();
                    if (seqTech==null)
                        seqTech = "Unknown";

                    // upload reads
                    UploadReadsOutput uro = ruc.uploadReads(new UploadReadsParams()
                                                            .withFwdFile(outputFileFQ.getPath())
                                                            .withSequencingTech(seqTech)
                                                            .withWsname(input.getWs())
                                                            .withName(outputReadsName));
                    objects.add(new WorkspaceObject()
                                .withRef(uro.getObjRef())
                                .withDescription("Chimera-filtered reads"));
                }

                // clean up
                java.io.File f = new java.io.File(fwdPath);
                f.delete();
                inputFileDerep.delete();
                inputFileDerepFA.delete();
                inputFileFA.delete();
                outputFile.delete();
                outputFileIDs.delete();
                outputFileFQ.delete();
                outputFileLog.delete();
                
                reportText += "Done with "+readsName+".\n\n";
            }
            
        }

        
        String[] report = makeReport(token,
                                     input.getWs(),
                                     reportText,
                                     warnings,
                                     objects);
        RunUchimeOutput rv = new RunUchimeOutput()
            .withReportName(report[0])
            .withReportRef(report[1]);

        return rv;
    }
}        
