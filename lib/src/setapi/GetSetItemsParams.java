
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
 * <p>Original spec-file type: GetSetItemsParams</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "set_refs"
})
public class GetSetItemsParams {

    @JsonProperty("set_refs")
    private List<SetReference> setRefs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("set_refs")
    public List<SetReference> getSetRefs() {
        return setRefs;
    }

    @JsonProperty("set_refs")
    public void setSetRefs(List<SetReference> setRefs) {
        this.setRefs = setRefs;
    }

    public GetSetItemsParams withSetRefs(List<SetReference> setRefs) {
        this.setRefs = setRefs;
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
        return ((((("GetSetItemsParams"+" [setRefs=")+ setRefs)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
