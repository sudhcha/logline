package com.vrc.logline.domain;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDiff {
    private static final Logger log = Logger.getLogger(FileDiff.class);
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
            String message = filePath1 + "|missing file";
            deltas.add(message);
            log.info(message);
            return this;
        }
        if (!file2.exists()) {
            String message = filePath2 + "|missing file";
            deltas.add(message);
            log.info(message);
            return this;
        }
        Patch patch = DiffUtils.diff(FileUtils.readLines(file1), FileUtils.readLines(file2));
        for (Delta delta : patch.getDeltas())
            deltas.add(delta.toString());

        log.info(name + "|" + deltas);
        return this;
    }

    public List<String> deltas() {
        return deltas;
    }

    public String name() {
        return name;
    }

}

