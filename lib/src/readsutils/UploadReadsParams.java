
package readsutils;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kbasecommon.SourceInfo;
import kbasecommon.StrainInfo;


/**
 * <p>Original spec-file type: UploadReadsParams</p>
 * <pre>
 * Input to the upload_reads function.
 * If local files are specified for upload, they must be uncompressed.
 * Files will be gzipped prior to upload.
 * Note that if a reverse read file is specified, it must be a local file
 * if the forward reads file is a local file, or a shock id if not.
 * Required parameters:
 * fwd_id - the id of the shock node containing the reads data file:
 *     either single end reads, forward/left reads, or interleaved reads.
 * - OR -
 * fwd_file - a local path to the reads data file: either single end
 *     reads, forward/left reads, or interleaved reads.
 * sequencing_tech - the sequencing technology used to produce the
 *     reads.
 * One of:
 * wsid - the id of the workspace where the reads will be saved
 *     (preferred).
 * wsname - the name of the workspace where the reads will be saved.
 * One of:
 * objid - the id of the workspace object to save over
 * name - the name to which the workspace object will be saved
 *     
 * Optional parameters:
 * rev_id - the shock node id containing the reverse/right reads for
 *     paired end, non-interleaved reads.
 * - OR -
 * rev_file - a local path to the reads data file containing the
 *     reverse/right reads for paired end, non-interleaved reads.
 * single_genome - whether the reads are from a single genome or a
 *     metagenome. Default is single genome.
 * strain - information about the organism strain
 *     that was sequenced.
 * source - information about the organism source.
 * interleaved - specify that the fwd reads file is an interleaved paired
 *     end reads file as opposed to a single end reads file. Default true,
 *     ignored if rev_id is specified.
 * read_orientation_outward - whether the read orientation is outward
 *     from the set of primers. Default is false and is ignored for
 *     single end reads.
 * insert_size_mean - the mean size of the genetic fragments. Ignored for
 *     single end reads.
 * insert_size_std_dev - the standard deviation of the size of the
 *     genetic fragments. Ignored for single end reads.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "fwd_id",
    "fwd_file",
    "wsid",
    "wsname",
    "objid",
    "name",
    "rev_id",
    "rev_file",
    "sequencing_tech",
    "single_genome",
    "strain",
    "source",
    "interleaved",
    "read_orientation_outward",
    "insert_size_mean",
    "insert_size_std_dev"
})
public class UploadReadsParams {

    @JsonProperty("fwd_id")
    private String fwdId;
    @JsonProperty("fwd_file")
    private String fwdFile;
    @JsonProperty("wsid")
    private Long wsid;
    @JsonProperty("wsname")
    private String wsname;
    @JsonProperty("objid")
    private Long objid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rev_id")
    private String revId;
    @JsonProperty("rev_file")
    private String revFile;
    @JsonProperty("sequencing_tech")
    private String sequencingTech;
    @JsonProperty("single_genome")
    private Long singleGenome;
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
    @JsonProperty("strain")
    private StrainInfo strain;
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
    @JsonProperty("interleaved")
    private Long interleaved;
    @JsonProperty("read_orientation_outward")
    private Long readOrientationOutward;
    @JsonProperty("insert_size_mean")
    private Double insertSizeMean;
    @JsonProperty("insert_size_std_dev")
    private Double insertSizeStdDev;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fwd_id")
    public String getFwdId() {
        return fwdId;
    }

    @JsonProperty("fwd_id")
    public void setFwdId(String fwdId) {
        this.fwdId = fwdId;
    }

    public UploadReadsParams withFwdId(String fwdId) {
        this.fwdId = fwdId;
        return this;
    }

    @JsonProperty("fwd_file")
    public String getFwdFile() {
        return fwdFile;
    }

    @JsonProperty("fwd_file")
    public void setFwdFile(String fwdFile) {
        this.fwdFile = fwdFile;
    }

    public UploadReadsParams withFwdFile(String fwdFile) {
        this.fwdFile = fwdFile;
        return this;
    }

    @JsonProperty("wsid")
    public Long getWsid() {
        return wsid;
    }

    @JsonProperty("wsid")
    public void setWsid(Long wsid) {
        this.wsid = wsid;
    }

    public UploadReadsParams withWsid(Long wsid) {
        this.wsid = wsid;
        return this;
    }

    @JsonProperty("wsname")
    public String getWsname() {
        return wsname;
    }

    @JsonProperty("wsname")
    public void setWsname(String wsname) {
        this.wsname = wsname;
    }

    public UploadReadsParams withWsname(String wsname) {
        this.wsname = wsname;
        return this;
    }

    @JsonProperty("objid")
    public Long getObjid() {
        return objid;
    }

    @JsonProperty("objid")
    public void setObjid(Long objid) {
        this.objid = objid;
    }

    public UploadReadsParams withObjid(Long objid) {
        this.objid = objid;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public UploadReadsParams withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("rev_id")
    public String getRevId() {
        return revId;
    }

    @JsonProperty("rev_id")
    public void setRevId(String revId) {
        this.revId = revId;
    }

    public UploadReadsParams withRevId(String revId) {
        this.revId = revId;
        return this;
    }

    @JsonProperty("rev_file")
    public String getRevFile() {
        return revFile;
    }

    @JsonProperty("rev_file")
    public void setRevFile(String revFile) {
        this.revFile = revFile;
    }

    public UploadReadsParams withRevFile(String revFile) {
        this.revFile = revFile;
        return this;
    }

    @JsonProperty("sequencing_tech")
    public String getSequencingTech() {
        return sequencingTech;
    }

    @JsonProperty("sequencing_tech")
    public void setSequencingTech(String sequencingTech) {
        this.sequencingTech = sequencingTech;
    }

    public UploadReadsParams withSequencingTech(String sequencingTech) {
        this.sequencingTech = sequencingTech;
        return this;
    }

    @JsonProperty("single_genome")
    public Long getSingleGenome() {
        return singleGenome;
    }

    @JsonProperty("single_genome")
    public void setSingleGenome(Long singleGenome) {
        this.singleGenome = singleGenome;
    }

    public UploadReadsParams withSingleGenome(Long singleGenome) {
        this.singleGenome = singleGenome;
        return this;
    }

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
    @JsonProperty("strain")
    public StrainInfo getStrain() {
        return strain;
    }

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
    @JsonProperty("strain")
    public void setStrain(StrainInfo strain) {
        this.strain = strain;
    }

    public UploadReadsParams withStrain(StrainInfo strain) {
        this.strain = strain;
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

    public UploadReadsParams withSource(SourceInfo source) {
        this.source = source;
        return this;
    }

    @JsonProperty("interleaved")
    public Long getInterleaved() {
        return interleaved;
    }

    @JsonProperty("interleaved")
    public void setInterleaved(Long interleaved) {
        this.interleaved = interleaved;
    }

    public UploadReadsParams withInterleaved(Long interleaved) {
        this.interleaved = interleaved;
        return this;
    }

    @JsonProperty("read_orientation_outward")
    public Long getReadOrientationOutward() {
        return readOrientationOutward;
    }

    @JsonProperty("read_orientation_outward")
    public void setReadOrientationOutward(Long readOrientationOutward) {
        this.readOrientationOutward = readOrientationOutward;
    }

    public UploadReadsParams withReadOrientationOutward(Long readOrientationOutward) {
        this.readOrientationOutward = readOrientationOutward;
        return this;
    }

    @JsonProperty("insert_size_mean")
    public Double getInsertSizeMean() {
        return insertSizeMean;
    }

    @JsonProperty("insert_size_mean")
    public void setInsertSizeMean(Double insertSizeMean) {
        this.insertSizeMean = insertSizeMean;
    }

    public UploadReadsParams withInsertSizeMean(Double insertSizeMean) {
        this.insertSizeMean = insertSizeMean;
        return this;
    }

    @JsonProperty("insert_size_std_dev")
    public Double getInsertSizeStdDev() {
        return insertSizeStdDev;
    }

    @JsonProperty("insert_size_std_dev")
    public void setInsertSizeStdDev(Double insertSizeStdDev) {
        this.insertSizeStdDev = insertSizeStdDev;
    }

    public UploadReadsParams withInsertSizeStdDev(Double insertSizeStdDev) {
        this.insertSizeStdDev = insertSizeStdDev;
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
        return ((((((((((((((((((((((((((((((((((("UploadReadsParams"+" [fwdId=")+ fwdId)+", fwdFile=")+ fwdFile)+", wsid=")+ wsid)+", wsname=")+ wsname)+", objid=")+ objid)+", name=")+ name)+", revId=")+ revId)+", revFile=")+ revFile)+", sequencingTech=")+ sequencingTech)+", singleGenome=")+ singleGenome)+", strain=")+ strain)+", source=")+ source)+", interleaved=")+ interleaved)+", readOrientationOutward=")+ readOrientationOutward)+", insertSizeMean=")+ insertSizeMean)+", insertSizeStdDev=")+ insertSizeStdDev)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
