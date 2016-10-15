
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
 * <p>Original spec-file type: DownloadReadsOutput</p>
 * <pre>
 * The output of the download method.
 * mapping<read_lib, DownloadedReadLibrary> files - a mapping
 *     of the read library workspace references to information
 *     about the converted data for each library.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "files"
})
public class DownloadReadsOutput {

    @JsonProperty("files")
    private Map<String, DownloadedReadLibrary> files;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("files")
    public Map<String, DownloadedReadLibrary> getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(Map<String, DownloadedReadLibrary> files) {
        this.files = files;
    }

    public DownloadReadsOutput withFiles(Map<String, DownloadedReadLibrary> files) {
        this.files = files;
        return this;
    }

    @JsonAnyGetter
    public Map<java.lang.String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(java.lang.String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public java.lang.String toString() {
        return ((((("DownloadReadsOutput"+" [files=")+ files)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
