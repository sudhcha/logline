package com.vrc.logline.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Context {
    private List<Line> lines;
    private int size;
    private boolean inForwardCollectMode;

    public Context() {
        this.lines = new ArrayList<Line>();
        this.size = 20;
    }

    public void add(String inputLine, String file, String thread) {
        if (lines.size() > size)
            lines.clear();
        lines.add(new Line(inputLine).ofFile(file).ofThread(thread));
    }

    public void getBackward(){

    }

}
