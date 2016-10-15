package readsutils;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JobState;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: ReadsUtils</p>
 * <pre>
 * Utilities for handling reads files.
 * </pre>
 */
public class ReadsUtilsClient {
    private JsonClientCaller caller;
    private long asyncJobCheckTimeMs = 100;
    private int asyncJobCheckTimeScalePercent = 150;
    private long asyncJobCheckMaxTimeMs = 300000;  // 5 minutes
    private String serviceVersion = "release";
    private static URL DEFAULT_URL = null;
    static {
        try {
            DEFAULT_URL = new URL("https://kbase.us/services/njs_wrapper");
        } catch (MalformedURLException mue) {
            throw new RuntimeException("Compile error in client - bad url compiled");
        }
    }

    /** Constructs a client with the default url and no user credentials.*/
    public ReadsUtilsClient() {
       caller = new JsonClientCaller(DEFAULT_URL);
    }


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public ReadsUtilsClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public ReadsUtilsClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public ReadsUtilsClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

    /** Constructs a client with the default URL.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public ReadsUtilsClient(AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(DEFAULT_URL, token);
    }

    /** Constructs a client with the default URL.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public ReadsUtilsClient(String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(DEFAULT_URL, user, password);
    }

    /** Get the token this client uses to communicate with the server.
     * @return the authorization token.
     */
    public AuthToken getToken() {
        return caller.getToken();
    }

    /** Get the URL of the service with which this client communicates.
     * @return the service URL.
     */
    public URL getURL() {
        return caller.getURL();
    }

    /** Set the timeout between establishing a connection to a server and
     * receiving a response. A value of zero or null implies no timeout.
     * @param milliseconds the milliseconds to wait before timing out when
     * attempting to read from a server.
     */
    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    /** Check if this client allows insecure http (vs https) connections.
     * @return true if insecure connections are allowed.
     */
    public boolean isInsecureHttpConnectionAllowed() {
        return caller.isInsecureHttpConnectionAllowed();
    }

    /** Deprecated. Use isInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    /** Set whether insecure http (vs https) connections should be allowed by
     * this client.
     * @param allowed true to allow insecure connections. Default false
     */
    public void setIsInsecureHttpConnectionAllowed(boolean allowed) {
        caller.setInsecureHttpConnectionAllowed(allowed);
    }

    /** Deprecated. Use setIsInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /** Set whether all SSL certificates, including self-signed certificates,
     * should be trusted.
     * @param trustAll true to trust all certificates. Default false.
     */
    public void setAllSSLCertificatesTrusted(final boolean trustAll) {
        caller.setAllSSLCertificatesTrusted(trustAll);
    }
    
    /** Check if this client trusts all SSL certificates, including
     * self-signed certificates.
     * @return true if all certificates are trusted.
     */
    public boolean isAllSSLCertificatesTrusted() {
        return caller.isAllSSLCertificatesTrusted();
    }
    /** Sets streaming mode on. In this case, the data will be streamed to
     * the server in chunks as it is read from disk rather than buffered in
     * memory. Many servers are not compatible with this feature.
     * @param streamRequest true to set streaming mode on, false otherwise.
     */
    public void setStreamingModeOn(boolean streamRequest) {
        caller.setStreamingModeOn(streamRequest);
    }

    /** Returns true if streaming mode is on.
     * @return true if streaming mode is on.
     */
    public boolean isStreamingModeOn() {
        return caller.isStreamingModeOn();
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }

    public long getAsyncJobCheckTimeMs() {
        return this.asyncJobCheckTimeMs;
    }

    public void setAsyncJobCheckTimeMs(long newValue) {
        this.asyncJobCheckTimeMs = newValue;
    }

    public int getAsyncJobCheckTimeScalePercent() {
        return this.asyncJobCheckTimeScalePercent;
    }

    public void setAsyncJobCheckTimeScalePercent(int newValue) {
        this.asyncJobCheckTimeScalePercent = newValue;
    }

    public long getAsyncJobCheckMaxTimeMs() {
        return this.asyncJobCheckMaxTimeMs;
    }

    public void setAsyncJobCheckMaxTimeMs(long newValue) {
        this.asyncJobCheckMaxTimeMs = newValue;
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String newValue) {
        this.serviceVersion = newValue;
    }

    protected <T> JobState<T> _checkJob(String jobId, TypeReference<List<JobState<T>>> retType) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(jobId);
        List<JobState<T>> res = caller.jsonrpcCall("ReadsUtils._check_job", args, retType, true, true);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: validateFASTQ</p>
     * <pre>
     * Validate a FASTQ file. The file extensions .fq, .fnq, and .fastq
     * are accepted. Note that prior to validation the file will be altered in
     * place to remove blank lines if any exist.
     * </pre>
     * @param   params   instance of list of type {@link readsutils.ValidateFASTQParams ValidateFASTQParams}
     * @return   parameter "out" of list of type {@link readsutils.ValidateFASTQOutput ValidateFASTQOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _validateFASTQSubmit(List<ValidateFASTQParams> params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("ReadsUtils._validateFASTQ_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: validateFASTQ</p>
     * <pre>
     * Validate a FASTQ file. The file extensions .fq, .fnq, and .fastq
     * are accepted. Note that prior to validation the file will be altered in
     * place to remove blank lines if any exist.
     * </pre>
     * @param   params   instance of list of type {@link readsutils.ValidateFASTQParams ValidateFASTQParams}
     * @return   parameter "out" of list of type {@link readsutils.ValidateFASTQOutput ValidateFASTQOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public List<ValidateFASTQOutput> validateFASTQ(List<ValidateFASTQParams> params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _validateFASTQSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<List<ValidateFASTQOutput>>>>> retType = new TypeReference<List<JobState<List<List<ValidateFASTQOutput>>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<List<ValidateFASTQOutput>>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: upload_reads</p>
     * <pre>
     * Loads a set of reads to KBase data stores.
     * </pre>
     * @param   params   instance of type {@link readsutils.UploadReadsParams UploadReadsParams}
     * @return   instance of type {@link readsutils.UploadReadsOutput UploadReadsOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _uploadReadsSubmit(UploadReadsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("ReadsUtils._upload_reads_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: upload_reads</p>
     * <pre>
     * Loads a set of reads to KBase data stores.
     * </pre>
     * @param   params   instance of type {@link readsutils.UploadReadsParams UploadReadsParams}
     * @return   instance of type {@link readsutils.UploadReadsOutput UploadReadsOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public UploadReadsOutput uploadReads(UploadReadsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _uploadReadsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<UploadReadsOutput>>>> retType = new TypeReference<List<JobState<List<UploadReadsOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<UploadReadsOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: download_reads</p>
     * <pre>
     * Download read libraries. Reads compressed with gzip or bzip are
     * automatically uncompressed.
     * </pre>
     * @param   params   instance of type {@link readsutils.DownloadReadsParams DownloadReadsParams}
     * @return   parameter "output" of type {@link readsutils.DownloadReadsOutput DownloadReadsOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _downloadReadsSubmit(DownloadReadsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("ReadsUtils._download_reads_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: download_reads</p>
     * <pre>
     * Download read libraries. Reads compressed with gzip or bzip are
     * automatically uncompressed.
     * </pre>
     * @param   params   instance of type {@link readsutils.DownloadReadsParams DownloadReadsParams}
     * @return   parameter "output" of type {@link readsutils.DownloadReadsOutput DownloadReadsOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public DownloadReadsOutput downloadReads(DownloadReadsParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _downloadReadsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<DownloadReadsOutput>>>> retType = new TypeReference<List<JobState<List<DownloadReadsOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<DownloadReadsOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    /**
     * <p>Original spec-file function name: export_reads</p>
     * <pre>
     * KBase downloader function. Packages a set of reads into a zip file and
     * stores the zip in shock.
     * </pre>
     * @param   params   instance of type {@link readsutils.ExportParams ExportParams}
     * @return   parameter "output" of type {@link readsutils.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    protected String _exportReadsSubmit(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<String>> retType = new TypeReference<List<String>>() {};
        List<String> res = caller.jsonrpcCall("ReadsUtils._export_reads_submit", args, retType, true, true, jsonRpcContext);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: export_reads</p>
     * <pre>
     * KBase downloader function. Packages a set of reads into a zip file and
     * stores the zip in shock.
     * </pre>
     * @param   params   instance of type {@link readsutils.ExportParams ExportParams}
     * @return   parameter "output" of type {@link readsutils.ExportOutput ExportOutput}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ExportOutput exportReads(ExportParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        String jobId = _exportReadsSubmit(params, jsonRpcContext);
        TypeReference<List<JobState<List<ExportOutput>>>> retType = new TypeReference<List<JobState<List<ExportOutput>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<ExportOutput>> res = _checkJob(jobId, retType);
            if (res.getFinished() != 0L)
                return res.getResult().get(0);
        }
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        if (this.serviceVersion != null) {
            if (jsonRpcContext == null || jsonRpcContext.length == 0 || jsonRpcContext[0] == null)
                jsonRpcContext = new RpcContext[] {new RpcContext()};
            jsonRpcContext[0].getAdditionalProperties().put("service_ver", this.serviceVersion);
        }
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<String>> retType1 = new TypeReference<List<String>>() {};
        List<String> res1 = caller.jsonrpcCall("ReadsUtils._status_submit", args, retType1, true, true, jsonRpcContext);
        String jobId = res1.get(0);
        TypeReference<List<JobState<List<Map<String, Object>>>>> retType2 = new TypeReference<List<JobState<List<Map<String, Object>>>>>() {};
        long asyncJobCheckTimeMs = this.asyncJobCheckTimeMs;
        while (true) {
            if (Thread.currentThread().isInterrupted())
                throw new JsonClientException("Thread was interrupted");
            try { 
                Thread.sleep(asyncJobCheckTimeMs);
            } catch(Exception ex) {
                throw new JsonClientException("Thread was interrupted", ex);
            }
            asyncJobCheckTimeMs = Math.min(asyncJobCheckTimeMs * this.asyncJobCheckTimeScalePercent / 100, this.asyncJobCheckMaxTimeMs);
            JobState<List<Map<String, Object>>> res2 = _checkJob(jobId, retType2);
            if (res2.getFinished() != 0L)
                return res2.getResult().get(0);
        }
    }
}
