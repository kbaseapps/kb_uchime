package kbuchime;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonServerMethod;
import us.kbase.common.service.JsonServerServlet;
import us.kbase.common.service.JsonServerSyslog;
import us.kbase.common.service.RpcContext;

//BEGIN_HEADER
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import us.kbase.common.service.Tuple11;
import us.kbase.common.service.UObject;
import us.kbase.workspace.ObjectIdentity;
import us.kbase.workspace.ObjectSaveData;
import us.kbase.workspace.ProvenanceAction;
import us.kbase.workspace.SaveObjectsParams;
import us.kbase.workspace.WorkspaceClient;
//END_HEADER

/**
 * <p>Original spec-file module name: kb_uchime</p>
 * <pre>
 * A KBase module: kb_uchime
 * </pre>
 */
public class KbUchimeServer extends JsonServerServlet {
    private static final long serialVersionUID = 1L;
    private static final String version = "0.0.1";
    private static final String gitUrl = "git@github.com:kbaseapps/kb_uchime.git";
    private static final String gitCommitHash = "8aa19650ca38648aa376eb6a7142b61b77e3bf08";

    //BEGIN_CLASS_HEADER
    private final String wsUrl;
    private final String serviceWizardUrl;
    //END_CLASS_HEADER

    public KbUchimeServer() throws Exception {
        super("kb_uchime");
        //BEGIN_CONSTRUCTOR
        wsUrl = config.get("workspace-url");
        serviceWizardUrl = config.get("service-wizard-url");
        //END_CONSTRUCTOR
    }

    /**
     * <p>Original spec-file function name: run_uchime</p>
     * <pre>
     * </pre>
     * @param   input   instance of type {@link kbuchime.RunUchimeInput RunUchimeInput}
     * @return   parameter "output" of type {@link kbuchime.RunUchimeOutput RunUchimeOutput}
     */
    @JsonServerMethod(rpc = "kb_uchime.run_uchime", async=true)
    public RunUchimeOutput runUchime(RunUchimeInput input, AuthToken authPart, RpcContext jsonRpcContext) throws Exception {
        RunUchimeOutput returnVal = null;
        //BEGIN run_uchime
        returnVal = KbUchimeImpl.runUchime(wsUrl,serviceWizardUrl,authPart,input);
        //END run_uchime
        return returnVal;
    }

    /**
     * <p>Original spec-file function name: version</p>
     * <pre>
     * returns version number of service
     * </pre>
     * @return   parameter "version" of String
     */
    @JsonServerMethod(rpc = "kb_uchime.version", async=true)
    public String version(RpcContext jsonRpcContext) throws Exception {
        String returnVal = null;
        //BEGIN version
        returnVal = "KB-UCHIME-1.0."+serialVersionUID;
        //END version
        return returnVal;
    }
    @JsonServerMethod(rpc = "kb_uchime.status")
    public Map<String, Object> status() {
        Map<String, Object> returnVal = null;
        //BEGIN_STATUS
        returnVal = new LinkedHashMap<String, Object>();
        returnVal.put("state", "OK");
        returnVal.put("message", "");
        returnVal.put("version", version);
        returnVal.put("git_url", gitUrl);
        returnVal.put("git_commit_hash", gitCommitHash);
        //END_STATUS
        return returnVal;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            new KbUchimeServer().startupServer(Integer.parseInt(args[0]));
        } else if (args.length == 3) {
            JsonServerSyslog.setStaticUseSyslog(false);
            JsonServerSyslog.setStaticMlogFile(args[1] + ".log");
            new KbUchimeServer().processRpcCall(new File(args[0]), new File(args[1]), args[2]);
        } else {
            System.out.println("Usage: <program> <server_port>");
            System.out.println("   or: <program> <context_json_file> <output_json_file> <token>");
            return;
        }
    }
}
