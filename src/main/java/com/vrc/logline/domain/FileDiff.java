package com.vrc.logline.domain;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import difflib.StringUtills;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDiff {
    private static final Logger log = Logger.getLogger(FileDiff.class);
    private String name;
    private String filePath1;
    private String filePath2;
    private String result;

    public FileDiff(String name, String filePath1, String filePath2) {
        this.name = name;
        this.filePath1 = filePath1;
        this.filePath2 = filePath2;
    }

    public FileDiff process() throws Exception {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        if (!file1.exists()) {
            result = filePath1 + "|missing file";
        }else if (!file2.exists()) {
            result = filePath2 + "|missing file";
        }else{
            Patch patch = DiffUtils.diff(FileUtils.readLines(file1), FileUtils.readLines(file2));
            result = StringUtils.join(patch.getDeltas(),"\n");
        }
        log.info(result);
        return this;
    }

    public String result() {
        return result;
    }

    public String name() {
        return name;
    }

}

