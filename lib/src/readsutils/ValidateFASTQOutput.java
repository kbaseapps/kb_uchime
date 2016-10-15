
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
 * <p>Original spec-file type: ValidateFASTQOutput</p>
 * <pre>
 * The output of the validateFASTQ function.
 * validated - whether the file validated successfully or not.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "validated"
})
public class ValidateFASTQOutput {

    @JsonProperty("validated")
    private Long validated;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("validated")
    public Long getValidated() {
        return validated;
    }

    @JsonProperty("validated")
    public void setValidated(Long validated) {
        this.validated = validated;
    }

    public ValidateFASTQOutput withValidated(Long validated) {
        this.validated = validated;
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
        return ((((("ValidateFASTQOutput"+" [validated=")+ validated)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
