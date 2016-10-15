
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
import us.kbase.common.service.Tuple11;


/**
 * <p>Original spec-file type: ReadsSetItem</p>
 * <pre>
 * When saving a ReadsSet, only 'ref' is required.  You should
 * never set 'info'.  'info' is provided optionally when fetching
 * the ReadsSet.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "ref",
    "label",
    "data_attachments",
    "info"
})
public class ReadsSetItem {

    @JsonProperty("ref")
    private java.lang.String ref;
    @JsonProperty("label")
    private java.lang.String label;
    @JsonProperty("data_attachments")
    private List<DataAttachment> dataAttachments;
    @JsonProperty("info")
    private Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> info;
    private Map<java.lang.String, Object> additionalProperties = new HashMap<java.lang.String, Object>();

    @JsonProperty("ref")
    public java.lang.String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(java.lang.String ref) {
        this.ref = ref;
    }

    public ReadsSetItem withRef(java.lang.String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("label")
    public java.lang.String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(java.lang.String label) {
        this.label = label;
    }

    public ReadsSetItem withLabel(java.lang.String label) {
        this.label = label;
        return this;
    }

    @JsonProperty("data_attachments")
    public List<DataAttachment> getDataAttachments() {
        return dataAttachments;
    }

    @JsonProperty("data_attachments")
    public void setDataAttachments(List<DataAttachment> dataAttachments) {
        this.dataAttachments = dataAttachments;
    }

    public ReadsSetItem withDataAttachments(List<DataAttachment> dataAttachments) {
        this.dataAttachments = dataAttachments;
        return this;
    }

    @JsonProperty("info")
    public Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> info) {
        this.info = info;
    }

    public ReadsSetItem withInfo(Tuple11 <Long, String, String, String, Long, String, Long, String, String, Long, Map<String, String>> info) {
        this.info = info;
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
        return ((((((((((("ReadsSetItem"+" [ref=")+ ref)+", label=")+ label)+", dataAttachments=")+ dataAttachments)+", info=")+ info)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
