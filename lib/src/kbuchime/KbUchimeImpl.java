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

import com.fasterxml.jackson.databind.*;

import static java.lang.ProcessBuilder.Redirect;

/**
   This class runs UCHIME (in VSEARCH) against a single read library
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
        String reportText = "";
        List<WorkspaceObject> objects = new ArrayList<WorkspaceObject>();
        List<String> warnings = null;

        // run UCHIME on each library
        for (int i=0; i<readsRefs.size(); i++) {
            readsRef = readsRefs.get(i);
            String readsName = readsNames.get(i);
            String outputReadsName = outputReadsNames.get(i);

            reportText += "Running UCHIME on "+readsName+" ("+readsRef+")\n";

            // download reads
            DownloadReadsOutput dro = ruc.downloadReads(new DownloadReadsParams()
                                                        .withReadLibraries(Arrays.asList(readsRef)));
            for (DownloadedReadLibrary drl : dro.getFiles().values()) {
                ReadsFiles rf = drl.getFiles();
                java.io.File f;
                String fwdPath = rf.getFwd();
                String revPath = rf.getRev();
                if (fwdPath != null) {
                    f = new java.io.File(fwdPath);
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
                    f = new java.io.File(revPath);
                    f.delete();
                }

                // write outputs to temp files
                java.io.File outputFileFA = java.io.File.createTempFile("reads", ".fa", tempDir);
                java.io.File outputFileHead = java.io.File.createTempFile("reads", ".txt", tempDir);
                java.io.File outputFileFQ = java.io.File.createTempFile("reads", ".fastq", tempDir);
                java.io.File outputFileLog = java.io.File.createTempFile("log", ".txt", tempDir);
                outputFileFA.delete();
                outputFileHead.delete();
                outputFileFQ.delete();
                outputFileLog.delete();

                // run UCHIME
                ProcessBuilder pb =
                    new ProcessBuilder("/kb/module/dependencies/bin/vsearch",
                                       "--uchime_denovo",
                                       fwdPath,
                                       "--nonchimeras",
                                       outputFileFA.getPath());
                pb.redirectErrorStream(true);
                pb.redirectOutput(Redirect.to(outputFileLog));
                pb.start().waitFor();

                // grab output into report
                List<String> lines = Files.readAllLines(Paths.get(outputFileLog.getPath()), Charset.defaultCharset());
                for (String line : lines) {
                    // should check for errors here
                    reportText += line+"\n";
                }

                // check that output actually generated
                long fileSize = outputFileFA.length();
                if (fileSize == 0L) {
                    reportText += "\nERROR: UCHIME did not generate any output.\n";
                    if (warnings == null)
                        warnings = new ArrayList<String>();
                    warnings.add("ERROR: UCHIME did not generate any output.");
                }
                else {
                    // get headers from fasta file
                    pb = new ProcessBuilder("/bin/sh",
                                            "-c",
                                            "grep -e '^>' "+outputFileFA.getPath()+" | cut -c 2-");
                    pb.redirectOutput(Redirect.to(outputFileHead));
                    pb.start().waitFor();

                    // extract subset of original fastq file
                    pb = new ProcessBuilder("seqtk",
                                            "subseq",
                                            fwdPath,
                                            outputFileHead.getPath());
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
                                .withDescription("UCHIME-filtered reads"));
                }

                // clean up
                f = new java.io.File(fwdPath);
                f.delete();
                outputFileFA.delete();
                outputFileHead.delete();
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
