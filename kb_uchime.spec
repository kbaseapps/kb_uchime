/*
A KBase module: kb_uchime
*/

module kb_uchime {
    typedef structure {
	string ws;
	string input_reads_name; /* may be ReadSet or SingleEndLibrary */
	string output_reads_name;
	string program_name; /* must be UCHIME or VSEARCH */
    } RunUchimeInput;

    typedef structure {
	string report_name;
	string report_ref;
    } RunUchimeOutput;

    funcdef run_uchime(RunUchimeInput input) returns (RunUchimeOutput output) authentication required;

    /* returns version number of service */
    funcdef version() returns (string version);
};
