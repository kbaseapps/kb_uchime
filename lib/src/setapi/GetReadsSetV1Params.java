
package setapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: GetReadsSetV1Params</p>
 * <pre>
 * ref - workspace reference to ReadsGroup object.
 * include_item_info - 1 or 0, if 1 additionally provides workspace info (with
 *                     metadata) for each Reads object in the Set
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "include_item_info",
    "ref_path_to_set"
})
public class GetReadsSetV1Params {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("include_item_info")
    private Long includeItemInfo;
    @JsonProperty("ref_path_to_set")
    private List<String> refPathToSet;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public GetReadsSetV1Params withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("include_item_info")
    public Long getIncludeItemInfo() {
        return includeItemInfo;
    }

    @JsonProperty("include_item_info")
    public void setIncludeItemInfo(Long includeItemInfo) {
        this.includeItemInfo = includeItemInfo;
    }

    public GetReadsSetV1Params withIncludeItemInfo(Long includeItemInfo) {
        this.includeItemInfo = includeItemInfo;
        return this;
    }

    @JsonProperty("ref_path_to_set")
    public List<String> getRefPathToSet() {
        return refPathToSet;
    }

    @JsonProperty("ref_path_to_set")
    public void setRefPathToSet(List<String> refPathToSet) {
        this.refPathToSet = refPathToSet;
    }

    public GetReadsSetV1Params withRefPathToSet(List<String> refPathToSet) {
        this.refPathToSet = refPathToSet;
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
        return ((((((((("GetReadsSetV1Params"+" [ref=")+ ref)+", includeItemInfo=")+ includeItemInfo)+", refPathToSet=")+ refPathToSet)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
