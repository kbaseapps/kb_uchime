
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
 * <p>Original spec-file type: GetSetItemsResult</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "sets"
})
public class GetSetItemsResult {

    @JsonProperty("sets")
    private List<SetInfo> sets;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sets")
    public List<SetInfo> getSets() {
        return sets;
    }

    @JsonProperty("sets")
    public void setSets(List<SetInfo> sets) {
        this.sets = sets;
    }

    public GetSetItemsResult withSets(List<SetInfo> sets) {
        this.sets = sets;
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
        return ((((("GetSetItemsResult"+" [sets=")+ sets)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
