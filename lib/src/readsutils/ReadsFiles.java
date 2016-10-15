
package readsutils;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: ReadsFiles</p>
 * <pre>
 * Reads file information.
 * Note that the file names provided are those *prior to* interleaving
 * or deinterleaving the reads.
 * string fwd - the path to the forward / left reads.
 * string fwd_name - the name of the forwards reads file from Shock, or
 *     if not available, from the Shock handle.
 * string rev - the path to the reverse / right reads. null if the reads
 *     are single end or interleaved.
 * string rev_name - the name of the reverse reads file from Shock, or
 *     if not available, from the Shock handle. null if the reads
 *     are single end or interleaved.
 * string otype - the original type of the reads. One of 'single',
 *     'paired', or 'interleaved'.
 * string type - one of 'single', 'paired', or 'interleaved'.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "fwd",
    "fwd_name",
    "rev",
    "rev_name",
    "otype",
    "type"
})
public class ReadsFiles {

    @JsonProperty("fwd")
    private String fwd;
    @JsonProperty("fwd_name")
    private String fwdName;
    @JsonProperty("rev")
    private String rev;
    @JsonProperty("rev_name")
    private String revName;
    @JsonProperty("otype")
    private String otype;
    @JsonProperty("type")
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fwd")
    public String getFwd() {
        return fwd;
    }

    @JsonProperty("fwd")
    public void setFwd(String fwd) {
        this.fwd = fwd;
    }

    public ReadsFiles withFwd(String fwd) {
        this.fwd = fwd;
        return this;
    }

    @JsonProperty("fwd_name")
    public String getFwdName() {
        return fwdName;
    }

    @JsonProperty("fwd_name")
    public void setFwdName(String fwdName) {
        this.fwdName = fwdName;
    }

    public ReadsFiles withFwdName(String fwdName) {
        this.fwdName = fwdName;
        return this;
    }

    @JsonProperty("rev")
    public String getRev() {
        return rev;
    }

    @JsonProperty("rev")
    public void setRev(String rev) {
        this.rev = rev;
    }

    public ReadsFiles withRev(String rev) {
        this.rev = rev;
        return this;
    }

    @JsonProperty("rev_name")
    public String getRevName() {
        return revName;
    }

    @JsonProperty("rev_name")
    public void setRevName(String revName) {
        this.revName = revName;
    }

    public ReadsFiles withRevName(String revName) {
        this.revName = revName;
        return this;
    }

    @JsonProperty("otype")
    public String getOtype() {
        return otype;
    }

    @JsonProperty("otype")
    public void setOtype(String otype) {
        this.otype = otype;
    }

    public ReadsFiles withOtype(String otype) {
        this.otype = otype;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public ReadsFiles withType(String type) {
        this.type = type;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return ((((((((((((((("ReadsFiles"+" [fwd=")+ fwd)+", fwdName=")+ fwdName)+", rev=")+ rev)+", revName=")+ revName)+", otype=")+ otype)+", type=")+ type)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
