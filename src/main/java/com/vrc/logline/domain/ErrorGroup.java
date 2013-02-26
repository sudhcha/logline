package com.vrc.logline.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ErrorGroup {
    private Set<Line> lines;

    public ErrorGroup(Set<Line> lines) {
        this.lines = lines;
    }

    public Map<String,Set<Line>> groupByTitle(){
        Map<String,Set<Line>> groups = new HashMap<String, Set<Line>>();

        for (Line line : lines) {
        }
        return groups;
    }
}
