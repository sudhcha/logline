package com.vrc.logline.domain;

import com.vrc.logline.repository.AllRules;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TimeLine {
    private static final Logger log = Logger.getLogger(TimeLine.class);
    private String folder;
    private AllRules allRules;

    private List<String> keys = new ArrayList<String>();
    private Set<Line> outputLines = new HashSet<Line>();
    private Set<Line> keyLines = new HashSet<Line>();
    private Set<Line> errorLines = new HashSet<Line>();

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
        StopWatch watch = new StopWatch();
        watch.start();
        File logDir = new File(folder);
        if (!logDir.exists()) return;
        recurse(logDir);
        watch.stop();
        log.info("Time taken to timeline: "+ watch);
    }

    private void recurse(File logDir) throws Exception {
        for (File file : logDir.listFiles())
            if (file.isDirectory()) recurse(file);
            else allRules.apply(file, outputLines);

        for (Line outputLine : outputLines)
            if (outputLine.isError()) errorLines.add(outputLine);
            else keyLines.add(outputLine);
    }

    public Map<String, List<Line>> keyLines() {
        return new LineGroup(keyLines).strip().byThread();
    }

    public Set<Line> errorLines() {
        return errorLines;
    }

}
