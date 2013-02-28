package com.vrc.logline.domain;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class FileDiff {
    private String name;
    private String cvs;
    private String server;

    public FileDiff(String name, String cvs, String server) {
        this.name = name;
        this.cvs = cvs;
        this.server = server;
    }

    public FileDiffResult process() throws Exception {
        Patch patch = DiffUtils.diff(
                FileUtils.readLines(new File(cvs)), 
                FileUtils.readLines(new File(server)));
        FileDiffResult result = new FileDiffResult(name);
        for (Delta delta: patch.getDeltas())
            result.add(delta.toString());
        return result;
    }
    
}

