
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
 * <p>Original spec-file type: SetReference</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "path_to_set"
})
public class SetReference {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("path_to_set")
    private List<String> pathToSet;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public SetReference withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("path_to_set")
    public List<String> getPathToSet() {
        return pathToSet;
    }

    @JsonProperty("path_to_set")
    public void setPathToSet(List<String> pathToSet) {
        this.pathToSet = pathToSet;
    }

    public SetReference withPathToSet(List<String> pathToSet) {
        this.pathToSet = pathToSet;
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
        return ((((((("SetReference"+" [ref=")+ ref)+", pathToSet=")+ pathToSet)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
