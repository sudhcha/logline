package com.vrc.logline.domain;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ErrorGroup {
    private static final Logger log = Logger.getLogger(ErrorGroup.class);
    private Set<Line> lines;

    public ErrorGroup(Set<Line> lines) {
        this.lines = lines;
    }

    public Map<String, Set<Line>> byTitle() {
        StopWatch watch = new StopWatch();
        watch.start();
        Map<String, Set<Line>> groups = new HashMap<String, Set<Line>>();
        for (Line line : lines) {
            if (!groups.containsKey(line.errorTitle()))
                groups.put(line.errorTitle(), new HashSet<Line>());
            groups.get(line.errorTitle()).add(line);
        }
        watch.stop();
        log.info("Time taken to group: " + watch + "|groups=" + groups.size());
        return groups;
    }
}
