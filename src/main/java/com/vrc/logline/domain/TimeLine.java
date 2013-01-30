package com.vrc.logline.domain;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TimeLine {
    private List<LogLine> logs = new ArrayList<LogLine>();
    private List<String> keys;

    public TimeLine(String keys) {
        for (String key : StringUtils.split(keys, ","))
            if (StringUtils.isNotBlank(key))
                this.keys.add(key);
    }

    public TimeLine add(String line) {
        if (StringUtils.isNotBlank(line) && containsKey(line))
            logs.add(new LogLine(line));
        return this;
    }

    private boolean containsKey(String line) {
        for (String key : keys)
            if (line.contains(key))
                return true;
        return false;
    }

    public void processLogs(){
        //identify timestamp patterns
        //identify thread numbers,
        //group them as transactions based on timestamp
        //get key interaction points from config file like other web-services, db and jms.
        //get a presentation form for user interaction diagram
    }

}
