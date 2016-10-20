
package kbasecommon;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * <p>Original spec-file type: SourceInfo</p>
 * <pre>
 * Information about the source of a piece of data.
 * source - the name of the source (e.g. NCBI, JGI, Swiss-Prot)
 * source_id - the ID of the data at the source
 * project_id - the ID of a project encompassing the data at the source
 * @optional source source_id project_id
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "source",
    "source_id",
    "project_id"
})
public class SourceInfo {

    @JsonProperty("source")
    private String source;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("project_id")
    private String projectId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    public SourceInfo withSource(String source) {
        this.source = source;
        return this;
    }

    @JsonProperty("source_id")
    public String getSourceId() {
        return sourceId;
    }

    @JsonProperty("source_id")
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public SourceInfo withSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    @JsonProperty("project_id")
    public String getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public SourceInfo withProjectId(String projectId) {
        this.projectId = projectId;
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
        return ((((((((("SourceInfo"+" [source=")+ source)+", sourceId=")+ sourceId)+", projectId=")+ projectId)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
