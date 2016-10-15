
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
 * <p>Original spec-file type: DownloadedReadLibrary</p>
 * <pre>
 * Information about each set of reads.
 * ReadsFiles files - the reads files.
 * string ref - the absolute workspace reference of the reads file, e.g
 *     workspace_id/object_id/version.
 * tern single_genome - whether the reads are from a single genome or a
 *     metagenome. null if unknown.
 * tern read_orientation_outward - whether the read orientation is outward
 *     from the set of primers. null if unknown or single ended reads.
 * string sequencing_tech - the sequencing technology used to produce the
 *     reads. null if unknown.
 * KBaseCommon.StrainInfo strain - information about the organism strain
 *     that was sequenced. null if unavailable.
 * KBaseCommon.SourceInfo source - information about the organism source.
 *     null if unavailable.
 * float insert_size_mean - the mean size of the genetic fragments. null
 *     if unavailable or single end reads.
 * float insert_size_std_dev - the standard deviation of the size of the
 *     genetic fragments. null if unavailable or single end reads.
 * int read_count - the number of reads in the this dataset. null if
 *     unavailable.
 * int read_size - the total size of the reads, in bases. null if
 *     unavailable.
 * float gc_content - the GC content of the reads. null if
 *     unavailable.
 * </pre>
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "files",
    "ref",
    "single_genome",
    "read_orientation_outward",
    "sequencing_tech",
    "strain",
    "source",
    "insert_size_mean",
    "insert_size_std_dev",
    "read_count",
    "read_size",
    "gc_content"
})
public class DownloadedReadLibrary {

    /**
     * <p>Original spec-file type: ReadsFiles</p>
     * <pre>
     * Reads file information.
     * Note that the file names provided are those *prior to* interleaving
     * or deinterleaving the reads.
     * string fwd - the path to the forward / left reads.
     * string fwd_name - the name of the forwards reads file from Shock, or
     *     if not available, from the Shock handle.
     * string rev - the path to the reverse / right reads. null if the reads
     *     are single end or interleaved.
     * string rev_name - the name of the reverse reads file from Shock, or
     *     if not available, from the Shock handle. null if the reads
     *     are single end or interleaved.
     * string otype - the original type of the reads. One of 'single',
     *     'paired', or 'interleaved'.
     * string type - one of 'single', 'paired', or 'interleaved'.
     * </pre>
     * 
     */
    @JsonProperty("files")
    private ReadsFiles files;
    @JsonProperty("ref")
    private String ref;
    @JsonProperty("single_genome")
    private String singleGenome;
    @JsonProperty("read_orientation_outward")
    private String readOrientationOutward;
    @JsonProperty("sequencing_tech")
    private String sequencingTech;
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
    @JsonProperty("insert_size_mean")
    private Double insertSizeMean;
    @JsonProperty("insert_size_std_dev")
    private Double insertSizeStdDev;
    @JsonProperty("read_count")
    private Long readCount;
    @JsonProperty("read_size")
    private Long readSize;
    @JsonProperty("gc_content")
    private Double gcContent;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * <p>Original spec-file type: ReadsFiles</p>
     * <pre>
     * Reads file information.
     * Note that the file names provided are those *prior to* interleaving
     * or deinterleaving the reads.
     * string fwd - the path to the forward / left reads.
     * string fwd_name - the name of the forwards reads file from Shock, or
     *     if not available, from the Shock handle.
     * string rev - the path to the reverse / right reads. null if the reads
     *     are single end or interleaved.
     * string rev_name - the name of the reverse reads file from Shock, or
     *     if not available, from the Shock handle. null if the reads
     *     are single end or interleaved.
     * string otype - the original type of the reads. One of 'single',
     *     'paired', or 'interleaved'.
     * string type - one of 'single', 'paired', or 'interleaved'.
     * </pre>
     * 
     */
    @JsonProperty("files")
    public ReadsFiles getFiles() {
        return files;
    }

    /**
     * <p>Original spec-file type: ReadsFiles</p>
     * <pre>
     * Reads file information.
     * Note that the file names provided are those *prior to* interleaving
     * or deinterleaving the reads.
     * string fwd - the path to the forward / left reads.
     * string fwd_name - the name of the forwards reads file from Shock, or
     *     if not available, from the Shock handle.
     * string rev - the path to the reverse / right reads. null if the reads
     *     are single end or interleaved.
     * string rev_name - the name of the reverse reads file from Shock, or
     *     if not available, from the Shock handle. null if the reads
     *     are single end or interleaved.
     * string otype - the original type of the reads. One of 'single',
     *     'paired', or 'interleaved'.
     * string type - one of 'single', 'paired', or 'interleaved'.
     * </pre>
     * 
     */
    @JsonProperty("files")
    public void setFiles(ReadsFiles files) {
        this.files = files;
    }

    public DownloadedReadLibrary withFiles(ReadsFiles files) {
        this.files = files;
        return this;
    }

    @JsonProperty("ref")
    public String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(String ref) {
        this.ref = ref;
    }

    public DownloadedReadLibrary withRef(String ref) {
        this.ref = ref;
        return this;
    }

    @JsonProperty("single_genome")
    public String getSingleGenome() {
        return singleGenome;
    }

    @JsonProperty("single_genome")
    public void setSingleGenome(String singleGenome) {
        this.singleGenome = singleGenome;
    }

    public DownloadedReadLibrary withSingleGenome(String singleGenome) {
        this.singleGenome = singleGenome;
        return this;
    }

    @JsonProperty("read_orientation_outward")
    public String getReadOrientationOutward() {
        return readOrientationOutward;
    }

    @JsonProperty("read_orientation_outward")
    public void setReadOrientationOutward(String readOrientationOutward) {
        this.readOrientationOutward = readOrientationOutward;
    }

    public DownloadedReadLibrary withReadOrientationOutward(String readOrientationOutward) {
        this.readOrientationOutward = readOrientationOutward;
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

    public DownloadedReadLibrary withSequencingTech(String sequencingTech) {
        this.sequencingTech = sequencingTech;
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

    public DownloadedReadLibrary withStrain(StrainInfo strain) {
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

    public DownloadedReadLibrary withSource(SourceInfo source) {
        this.source = source;
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

    public DownloadedReadLibrary withInsertSizeMean(Double insertSizeMean) {
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

    public DownloadedReadLibrary withInsertSizeStdDev(Double insertSizeStdDev) {
        this.insertSizeStdDev = insertSizeStdDev;
        return this;
    }

    @JsonProperty("read_count")
    public Long getReadCount() {
        return readCount;
    }

    @JsonProperty("read_count")
    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public DownloadedReadLibrary withReadCount(Long readCount) {
        this.readCount = readCount;
        return this;
    }

    @JsonProperty("read_size")
    public Long getReadSize() {
        return readSize;
    }

    @JsonProperty("read_size")
    public void setReadSize(Long readSize) {
        this.readSize = readSize;
    }

    public DownloadedReadLibrary withReadSize(Long readSize) {
        this.readSize = readSize;
        return this;
    }

    @JsonProperty("gc_content")
    public Double getGcContent() {
        return gcContent;
    }

    @JsonProperty("gc_content")
    public void setGcContent(Double gcContent) {
        this.gcContent = gcContent;
    }

    public DownloadedReadLibrary withGcContent(Double gcContent) {
        this.gcContent = gcContent;
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
        return ((((((((((((((((((((((((((("DownloadedReadLibrary"+" [files=")+ files)+", ref=")+ ref)+", singleGenome=")+ singleGenome)+", readOrientationOutward=")+ readOrientationOutward)+", sequencingTech=")+ sequencingTech)+", strain=")+ strain)+", source=")+ source)+", insertSizeMean=")+ insertSizeMean)+", insertSizeStdDev=")+ insertSizeStdDev)+", readCount=")+ readCount)+", readSize=")+ readSize)+", gcContent=")+ gcContent)+", additionalProperties=")+ additionalProperties)+"]");
    }

}
