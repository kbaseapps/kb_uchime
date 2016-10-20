
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
 * <p>Original spec-file type: Location</p>
 * <pre>
 * Information about a location.
 * lat - latitude of the site, recorded as a decimal number. North latitudes
 *     are positive values and south latitudes are negative numbers.
 * lon - longitude of the site, recorded as a decimal number. West
 *     longitudes are positive values and east longitudes are negative
 *     numbers.
 * elevation - elevation of the site, expressed in meters above sea level.
 *     Negative values are allowed.
 * date - date of an event at this location (for example, sample
 *     collection), expressed in the format YYYY-MM-DDThh:mm:ss.SSSZ
 * description - a free text description of the location and, if applicable,
 *     the associated event.
 * @optional date description
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "lat",
    "lon",
    "elevation",
    "date",
    "description"
})
public class Location {

    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("elevation")
    private Double elevation;
    @JsonProperty("date")
    private String date;
    @JsonProperty("description")
    private String description;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Location withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Location withLon(Double lon) {
        this.lon = lon;
        return this;
    }

    @JsonProperty("elevation")
    public Double getElevation() {
        return elevation;
    }

    @JsonProperty("elevation")
    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Location withElevation(Double elevation) {
        this.elevation = elevation;
        return this;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    public Location withDate(String date) {
        this.date = date;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Location withDescription(String description) {
        this.description = description;
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
        return ((((((((((((("Location"+" [lat=")+ lat)+", lon=")+ lon)+", elevation=")+ elevation)+", date=")+ date)+", description=")+ description)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
