
package setapi;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import us.kbase.common.service.Tuple11;


/**
 * <p>Original spec-file type: SaveReadsSetV1Result</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "set_ref",
    "set_info"
})
public class SaveReadsSetV1Result {

    @JsonProperty("set_ref")
    private java.lang.String setRef;
    @JsonProperty("set_info")
    private Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> setInfo;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("set_ref")
    public java.lang.String getSetRef() {
        return setRef;
    }

    @JsonProperty("set_ref")
    public void setSetRef(java.lang.String setRef) {
        this.setRef = setRef;
    }

    public SaveReadsSetV1Result withSetRef(java.lang.String setRef) {
        this.setRef = setRef;
        return this;
    }

    @JsonProperty("set_info")
    public Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> getSetInfo() {
        return setInfo;
    }

    @JsonProperty("set_info")
    public void setSetInfo(Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> setInfo) {
        this.setInfo = setInfo;
    }

    public SaveReadsSetV1Result withSetInfo(Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> setInfo) {
        this.setInfo = setInfo;
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
        return ((((((("SaveReadsSetV1Result"+" [setRef=")+ setRef)+", setInfo=")+ setInfo)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
