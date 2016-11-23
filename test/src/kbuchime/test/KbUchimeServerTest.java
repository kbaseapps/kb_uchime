package kbuchime.test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.Assert;

import org.ini4j.Ini;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kbuchime.*;
import us.kbase.kbasereport.*;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UObject;
import us.kbase.workspace.*;

import us.kbase.common.service.RpcContext;


public class KbUchimeServerTest {
    private static AuthToken token = null;
    private static Map<String, String> config = null;
    private static WorkspaceClient wsClient = null;
    private static String wsName = null;
    private static KbUchimeServer impl = null;
    
    @BeforeClass
    public static void init() throws Exception {
        token = new AuthToken(System.getenv("KB_AUTH_TOKEN"));
        String configFilePath = System.getenv("KB_DEPLOYMENT_CONFIG");
        File deploy = new File(configFilePath);
        Ini ini = new Ini(deploy);
        config = ini.get("kb_uchime");
        wsClient = new WorkspaceClient(new URL(config.get("workspace-url")), token);
        wsClient.setAuthAllowedForHttp(true);
        // These lines are necessary because we don't want to start linux syslog bridge service
        JsonServerSyslog.setStaticUseSyslog(false);
        JsonServerSyslog.setStaticMlogFile(new File(config.get("scratch"), "test.log").getAbsolutePath());
        impl = new KbUchimeServer();
    }
    
    private static String getWsName() throws Exception {
        if (wsName == null) {
            long suffix = System.currentTimeMillis();
            wsName = "test_KbUchime_" + suffix;
            wsClient.createWorkspace(new CreateWorkspaceParams().withWorkspace(wsName));
        }
        return wsName;
    }
    
    private static RpcContext getContext() {
        return new RpcContext().withProvenance(Arrays.asList(new ProvenanceAction()
            .withService("kb_uchime").withMethod("please_never_use_it_in_production")
            .withMethodParams(new ArrayList<UObject>())));
    }
    
    @AfterClass
    public static void cleanup() {
        if (wsName != null) {
            try {
                wsClient.deleteWorkspace(new WorkspaceIdentity().withWorkspace(wsName));
                System.out.println("Test workspace was deleted");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void testRunOldUchime() throws Exception {
        RunUchimeInput input = new RunUchimeInput()
            .withWs("jmc:1477003527922")
            .withInputReadsName("GBVT06H_sample")
            .withOutputReadsName("GBVT06H_old_uchime_filtered")
            .withProgramName("UCHIME");
        RunUchimeOutput rv = impl.runUchime(input, token, (RpcContext)null);
        Assert.assertNotNull(rv);
        String reportRef = rv.getReportRef();
        Assert.assertNotNull(reportRef);
        Report report = wsClient.getObjects(Arrays.asList(new ObjectIdentity().withRef(reportRef))).get(0).getData().asClassInstance(us.kbase.kbasereport.Report.class);
        Assert.assertNotNull(report);
        System.out.println(report.getTextMessage());
    }

    @Test
    public void testRunVSearchUchime() throws Exception {
        RunUchimeInput input = new RunUchimeInput()
            .withWs("jmc:1477003527922")
            .withInputReadsName("GBVT06H_sample")
            .withOutputReadsName("GBVT06H_vsearch_uchime_filtered")
            .withProgramName("VSEARCH");
        RunUchimeOutput rv = impl.runUchime(input, token, (RpcContext)null);
        Assert.assertNotNull(rv);
        String reportRef = rv.getReportRef();
        Assert.assertNotNull(reportRef);
        Report report = wsClient.getObjects(Arrays.asList(new ObjectIdentity().withRef(reportRef))).get(0).getData().asClassInstance(us.kbase.kbasereport.Report.class);
        Assert.assertNotNull(report);
        System.out.println(report.getTextMessage());
    }
}
