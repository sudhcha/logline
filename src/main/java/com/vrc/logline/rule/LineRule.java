package com.vrc.logline.rule;

import com.vrc.logline.repository.AllLines;

public interface LineRule {

    void end();

    void start();

    void process(AllLines allLines);
}
