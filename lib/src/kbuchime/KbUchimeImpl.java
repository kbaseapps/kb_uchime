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

import com.fasterxml.jackson.databind.*;

import static java.lang.ProcessBuilder.Redirect;

/**
   This class runs UCHIME (in VSEARCH) against a single read library
   or set of reads
*/
public class KbUchimeImpl {
    protected static java.io.File tempDir = new java.io.File("/kb/module/work/");

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

        KBaseReportClient rc = new KBaseReportClient(token);
        ReportInfo ri = rc.create(new CreateParams()
                                  .withReport(report)
                                  .withWorkspaceName(ws));
        
        return new String[] { ri.getName(), ri.getRef() };
    }
    

    /**
       Runs UCHIME on a single read set
    */
    public static RunUchimeOutput runUchime(String wsURL,
                                            AuthToken token,
                                            RunUchimeInput input) throws Exception {

        WorkspaceClient wc = createWsClient(wsURL,token);

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
            SetAPIClient sc = new SetAPIClient(token);
            GetReadsSetV1Result readSetObj = sc.getReadsSetV1(new GetReadsSetV1Params().withRef(readsRef).withIncludeItemInfo(1L));
            for (ReadsSetItem rsi : readSetObj.getData().getItems()) {
                readsRefs.add(rsi.getRef());
                readsNames.add(rsi.getInfo().getE2());
                outputReadsNames.add(rsi.getInfo().getE2()+"_uchime");
            }
        }
        else {
            // single reads object, not a set
            readsRefs.add(readsRef);
            readsNames.add(input.getInputReadsName());
            outputReadsNames.add(input.getOutputReadsName());
        }

        // start generating report
        String reportText = "";
        List<WorkspaceObject> objects = new ArrayList<WorkspaceObject>();
        for (int i=0; i<readsRefs.size(); i++) {
            readsRef = readsRefs.get(i);
            String readsName = readsNames.get(i);
            String outputReadsName = outputReadsNames.get(i);

            reportText += "Running UCHIME on "+readsName+" ("+readsName+")\n";

            
        }

        
        String[] report = makeReport(token,
                                     input.getWs(),
                                     reportText,
                                     null,
                                     objects);
        RunUchimeOutput rv = new RunUchimeOutput()
            .withReportName(report[0])
            .withReportRef(report[1]);

        return rv;
    }
}        
