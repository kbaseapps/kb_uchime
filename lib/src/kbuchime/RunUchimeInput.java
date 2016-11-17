
package kbuchime;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: RunUchimeInput</p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ws",
    "input_reads_name",
    "output_reads_name",
    "program_name"
})
public class RunUchimeInput {

    @JsonProperty("ws")
    private String ws;
    @JsonProperty("input_reads_name")
    private String inputReadsName;
    @JsonProperty("output_reads_name")
    private String outputReadsName;
    @JsonProperty("program_name")
    private String programName;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ws")
    public String getWs() {
        return ws;
    }

    @JsonProperty("ws")
    public void setWs(String ws) {
        this.ws = ws;
    }

    public RunUchimeInput withWs(String ws) {
        this.ws = ws;
        return this;
    }

    @JsonProperty("input_reads_name")
    public String getInputReadsName() {
        return inputReadsName;
    }

    @JsonProperty("input_reads_name")
    public void setInputReadsName(String inputReadsName) {
        this.inputReadsName = inputReadsName;
    }

    public RunUchimeInput withInputReadsName(String inputReadsName) {
        this.inputReadsName = inputReadsName;
        return this;
    }

    @JsonProperty("output_reads_name")
    public String getOutputReadsName() {
        return outputReadsName;
    }

    @JsonProperty("output_reads_name")
    public void setOutputReadsName(String outputReadsName) {
        this.outputReadsName = outputReadsName;
    }

    public RunUchimeInput withOutputReadsName(String outputReadsName) {
        this.outputReadsName = outputReadsName;
        return this;
    }

    @JsonProperty("program_name")
    public String getProgramName() {
        return programName;
    }

    @JsonProperty("program_name")
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public RunUchimeInput withProgramName(String programName) {
        this.programName = programName;
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
        return ((((((((((("RunUchimeInput"+" [ws=")+ ws)+", inputReadsName=")+ inputReadsName)+", outputReadsName=")+ outputReadsName)+", programName=")+ programName)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
