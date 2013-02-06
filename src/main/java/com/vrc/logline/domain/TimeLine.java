package com.vrc.logline.domain;

import com.vrc.logline.repository.AllRules;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TimeLine {

    private String folder;
    private AllRules allRules;

    private List<String> keys = new ArrayList<String>();
    private List<Line> outputLines = new ArrayList<Line>();
    private List<Line> keyLines = new ArrayList<Line>();
    private List<Line> errorLines = new ArrayList<Line>();

    public TimeLine(String keys, String folder) {
        this.folder = folder;
        this.keys = split(keys);
        this.allRules = new AllRules(this.keys);
    }

    private List<String> split(String keys) {
        List<String> splitKeys = new ArrayList<String>();
        for (String key : StringUtils.split(keys, ","))
            if (StringUtils.isNotBlank(key))
                splitKeys.add(key);
        return splitKeys;
    }

    public void process() throws Exception {
        File logDir = new File(folder);
        if (!logDir.exists()) return;
        recurse(logDir);
    }

    private void recurse(File logDir) throws IOException {
        for (File file : logDir.listFiles()) {
            if (file.isDirectory())
                recurse(file);
            else
                for (String inputLine : FileUtils.readLines(file))
                    allRules.apply(inputLine, outputLines);
        }
        for (Line outputLine : outputLines)
            if (outputLine.isError())
                errorLines.add(outputLine);
            else
                keyLines.add(outputLine);
    }

    public List<Line> outputLines() {
        return outputLines;
    }

    public List<Line> keyLines() {
        return keyLines;
    }

    public List<Line> errorLines() {
        return errorLines;
    }

}
