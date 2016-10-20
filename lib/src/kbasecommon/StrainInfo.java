
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
 * <p>Original spec-file type: StrainInfo</p>
 * <pre>
 * Information about a strain.
 * genetic_code - the genetic code of the strain.
 *     See http://www.ncbi.nlm.nih.gov/Taxonomy/Utils/wprintgc.cgi?mode=c
 * genus - the genus of the strain
 * species - the species of the strain
 * strain - the identifier for the strain
 * source - information about the source of the strain
 * organelle - the organelle of interest for the related data (e.g.
 *     mitochondria)
 * ncbi_taxid - the NCBI taxonomy ID of the strain
 * location - the location from which the strain was collected
 * @optional genetic_code source ncbi_taxid organelle location
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "genetic_code",
    "genus",
    "species",
    "strain",
    "organelle",
    "source",
    "ncbi_taxid",
    "location"
})
public class StrainInfo {

    @JsonProperty("genetic_code")
    private Long geneticCode;
    @JsonProperty("genus")
    private String genus;
    @JsonProperty("species")
    private String species;
    @JsonProperty("strain")
    private String strain;
    @JsonProperty("organelle")
    private String organelle;
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
    @JsonProperty("source")
    private SourceInfo source;
    @JsonProperty("ncbi_taxid")
    private Long ncbiTaxid;
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
    @JsonProperty("location")
    private Location location;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genetic_code")
    public Long getGeneticCode() {
        return geneticCode;
    }

    @JsonProperty("genetic_code")
    public void setGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
    }

    public StrainInfo withGeneticCode(Long geneticCode) {
        this.geneticCode = geneticCode;
        return this;
    }

    @JsonProperty("genus")
    public String getGenus() {
        return genus;
    }

    @JsonProperty("genus")
    public void setGenus(String genus) {
        this.genus = genus;
    }

    public StrainInfo withGenus(String genus) {
        this.genus = genus;
        return this;
    }

    @JsonProperty("species")
    public String getSpecies() {
        return species;
    }

    @JsonProperty("species")
    public void setSpecies(String species) {
        this.species = species;
    }

    public StrainInfo withSpecies(String species) {
        this.species = species;
        return this;
    }

    @JsonProperty("strain")
    public String getStrain() {
        return strain;
    }

    @JsonProperty("strain")
    public void setStrain(String strain) {
        this.strain = strain;
    }

    public StrainInfo withStrain(String strain) {
        this.strain = strain;
        return this;
    }

    @JsonProperty("organelle")
    public String getOrganelle() {
        return organelle;
    }

    @JsonProperty("organelle")
    public void setOrganelle(String organelle) {
        this.organelle = organelle;
    }

    public StrainInfo withOrganelle(String organelle) {
        this.organelle = organelle;
        return this;
    }

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
    @JsonProperty("source")
    public SourceInfo getSource() {
        return source;
    }

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
    @JsonProperty("source")
    public void setSource(SourceInfo source) {
        this.source = source;
    }

    public StrainInfo withSource(SourceInfo source) {
        this.source = source;
        return this;
    }

    @JsonProperty("ncbi_taxid")
    public Long getNcbiTaxid() {
        return ncbiTaxid;
    }

    @JsonProperty("ncbi_taxid")
    public void setNcbiTaxid(Long ncbiTaxid) {
        this.ncbiTaxid = ncbiTaxid;
    }

    public StrainInfo withNcbiTaxid(Long ncbiTaxid) {
        this.ncbiTaxid = ncbiTaxid;
        return this;
    }

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
    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

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
    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public StrainInfo withLocation(Location location) {
        this.location = location;
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
        return ((((((((((((((((((("StrainInfo"+" [geneticCode=")+ geneticCode)+", genus=")+ genus)+", species=")+ species)+", strain=")+ strain)+", organelle=")+ organelle)+", source=")+ source)+", ncbiTaxid=")+ ncbiTaxid)+", location=")+ location)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
