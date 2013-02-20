package com.vrc.logline.domain;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import java.util.*;

public class LineGroup {
    private static final Logger log = Logger.getLogger(LineGroup.class);
    private Settings settings = new Settings();
    private Set<Line> lines;

    public LineGroup(Set<Line> lines) {
        this.lines = lines;
    }

    public Map<String, List<Line>> byThread() {
        StopWatch watch = new StopWatch();
        watch.start();
        Map<String, List<Line>> groups = new HashMap<String, List<Line>>();
        for (Line line : lines) {
            if (!groups.containsKey(line.getThread()))
                groups.put(line.getThread(), new ArrayList<Line>());
            groups.get(line.getThread()).add(line);
        }
        for (List<Line> lines : groups.values())
            Collections.sort(lines);
        watch.stop();
        System.out.println(groups.size());
        log.info("Time taken to group: "+watch);
        return groups;
    }

    public LineGroup strip() {
        StopWatch watch = new StopWatch();
        watch.start();
        for (Line line : lines)
            line.replace(settings.shorts());
        watch.stop();
        log.info("Time taken to strip: "+watch);
        return this;
    }
}
