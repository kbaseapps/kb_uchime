package kbuchime;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
import java.util.zip.*;
import java.net.URL;

import us.kbase.auth.AuthService;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.*;
import us.kbase.workspace.*;
import us.kbase.common.utils.FastaWriter;

import us.kbase.common.utils.AlignUtil;
import us.kbase.common.utils.CorrectProcess;
import us.kbase.workspace.ObjectData;
import us.kbase.workspace.ObjectIdentity;

import com.fasterxml.jackson.databind.*;

import static java.lang.ProcessBuilder.Redirect;

/**
   This class runs UCHIME (in VSEARCH) against a single read library
   or set of reads
*/
public class KbUchimeImpl {
    protected static File tempDir = new File("/kb/module/work/");

    /**
       Runs UCHIME on a single read set
    */
    public static RunUchimeOutput runUchime(String wsURL,
                                            AuthToken token,
                                            RunUchimeInput input) throws Exception {
        String[] report = new String[2];
        RunUchimeOutput rv = new RunUchimeOutput()
            .withReportName(report[0])
            .withReportRef(report[1]);

        return rv;
    }
}        
