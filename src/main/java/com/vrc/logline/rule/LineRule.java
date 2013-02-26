package com.vrc.logline.rule;

import com.vrc.logline.repository.AllLines;

import java.text.ParseException;

public interface LineRule {

    void end();

    void start();

    void process(AllLines allLines) throws Exception;
}
