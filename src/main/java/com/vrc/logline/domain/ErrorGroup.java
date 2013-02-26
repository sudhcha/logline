package com.vrc.logline.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ErrorGroup {
    private Set<Line> lines;

    public ErrorGroup(Set<Line> lines) {
        this.lines = lines;
    }

    public Map<String,Set<Line>> byTitle(){
        Map<String,Set<Line>> groups = new HashMap<String, Set<Line>>();
        for (Line line : lines) {
            if(!groups.containsKey(line.errorTitle()))
                groups.put(line.errorTitle(), new HashSet<Line>());
            groups.get(line.errorTitle()).add(line);
        }
        System.out.println("Error groups:"+groups.size());
        return groups;
    }
}
