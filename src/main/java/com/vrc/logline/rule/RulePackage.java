package com.vrc.logline.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RulePackage {
    private List<String> threads = new ArrayList<String>();
    private List<String> keys = new ArrayList<String>();


    public RulePackage addKeys(List<String> keys) {
        this.keys.addAll(keys);
        return this;
    }

    public RulePackage addThread(String thread) {
        this.threads.add(thread);
        return this;
    }

    public List<String> threads() {
        return threads;
    }

    public List<String> keys() {
        return keys;
    }
}
