package com.vrc.logline.domain;

import java.util.*;

public class LineGroup {

    private Set<Line> lines;

    public LineGroup(Set<Line> lines) {
        this.lines = lines;
    }

    public Map<String, List<Line>> byThread() {
        Map<String, List<Line>> groups = new HashMap<String, List<Line>>();
        for (Line line : lines) {
            if (!groups.containsKey(line.getThread()))
                groups.put(line.getThread(), new ArrayList<Line>());
            groups.get(line.getThread()).add(line);
        }
        for (List<Line> lines : groups.values())
           Collections.sort(lines);
        return groups;
    }
}
