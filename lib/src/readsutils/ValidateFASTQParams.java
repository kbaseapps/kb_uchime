
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
 * <p>Original spec-file type: ValidateFASTQParams</p>
 * <pre>
 * Input to the validateFASTQ function.
 *     Required parameters:
 *     file_path - the path to the file to validate.
 *     
 *     Optional parameters:
 *     interleaved - whether the file is interleaved or not. Setting this to
 *         true disables sequence ID checks.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "file_path",
    "interleaved"
})
public class ValidateFASTQParams {

    @JsonProperty("file_path")
    private String filePath;
    @JsonProperty("interleaved")
    private Long interleaved;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("file_path")
    public String getFilePath() {
        return filePath;
    }

    @JsonProperty("file_path")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ValidateFASTQParams withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @JsonProperty("interleaved")
    public Long getInterleaved() {
        return interleaved;
    }

    @JsonProperty("interleaved")
    public void setInterleaved(Long interleaved) {
        this.interleaved = interleaved;
    }

    public ValidateFASTQParams withInterleaved(Long interleaved) {
        this.interleaved = interleaved;
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
        return ((((((("ValidateFASTQParams"+" [filePath=")+ filePath)+", interleaved=")+ interleaved)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
