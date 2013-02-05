package com.vrc.logline.domain;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TimeLine {
    private String folder;
    private List<String> keys = new ArrayList<String>();
    private List<Line> lines = new ArrayList<Line>();

    public TimeLine(String keys, String folder) {
        this.folder = folder;
        for (String key : StringUtils.split(keys, ","))
            if (StringUtils.isNotBlank(key))
                this.keys.add(key);
    }

    public void process() throws Exception {
        File logDir = new File(folder);
        if(!logDir.exists()) return;

        for (File file : logDir.listFiles())
            for(String line: FileUtils.readLines(file))
                if (StringUtils.isNotBlank(line) && containsKey(line))
                    lines.add(new Line(line));

        //identify timestamp patterns
        //identify thread numbers,
        //group them as transactions based on timestamp
        //get key interaction points from config file like other web-services, db and jms.
        //get a presentation form for user interaction diagram
    }

    private boolean containsKey(String line) {
        for (String key : keys)
            if (line.contains(key))
                return true;
        return false;
    }

    public List<Line> lines() {
        return lines;
    }
}
