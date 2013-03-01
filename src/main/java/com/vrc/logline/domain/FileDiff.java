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
    private String filePath1;
    private String filePath2;
    private List<String> deltas;

    public FileDiff(String name, String filePath1, String filePath2) {
        this.name = name;
        this.filePath1 = filePath1;
        this.filePath2 = filePath2;
        this.deltas = new ArrayList<String>();
    }

    public FileDiff process() throws Exception {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        if (!file1.exists()) {
            deltas.add(filePath1 + "|missing file");
            return this;
        }
        if (!file2.exists()) {
            deltas.add(filePath2 + "|missing file");
            return this;
        }
        Patch patch = DiffUtils.diff(FileUtils.readLines(file1), FileUtils.readLines(file2));
        for (Delta delta : patch.getDeltas())
            deltas.add(delta.toString());
        return this;
    }

    public List<String> deltas() {
        return deltas;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "FileDiff{" +
                "name='" + name + '\'' +
                ", file1='" + filePath1 + '\'' +
                ", file2='" + filePath2 + '\'' +
                ", deltas=" + deltas +
                '}';
    }
}

