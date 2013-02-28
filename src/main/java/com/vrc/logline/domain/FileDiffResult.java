package com.vrc.logline.domain;

import java.util.ArrayList;
import java.util.List;

public class FileDiffResult {
    
    private String name;
    private List<String> deltas = new ArrayList<>();

    public FileDiffResult(String name) {
        this.name = name;
    }

    public void add(String delta){
        this.deltas.add(delta);
    }
}
