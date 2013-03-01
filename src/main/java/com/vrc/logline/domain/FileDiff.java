package com.vrc.logline.domain;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDiff {
    private String name;
    private String file1;
    private String file2;
    private List<String> deltas;

    public FileDiff(String name, String file1, String file2) {
        this.name = name;
        this.file1 = file1;
        this.file2 = file2;
        this.deltas = new ArrayList<String>();
    }

    public void process() throws Exception {
        Patch patch = DiffUtils.diff(
                FileUtils.readLines(new File(file1)),
                FileUtils.readLines(new File(file2)));
        for (Delta delta : patch.getDeltas())
            deltas.add(delta.toString());
    }

    public List<String> deltas() {
        return deltas;
    }

    public String name() {
        return name;
    }
}

