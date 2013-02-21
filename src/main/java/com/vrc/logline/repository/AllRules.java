package com.vrc.logline.repository;

import com.vrc.logline.rule.ExceptionRule;
import com.vrc.logline.rule.KeyRule;
import com.vrc.logline.rule.LineRule;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AllRules {
    private static final Logger log = Logger.getLogger(AllRules.class);
    private List<LineRule> lineRules = new ArrayList<LineRule>();

    public AllRules(List<String> keys) {
        this.lineRules.add(new KeyRule(keys));
        this.lineRules.add(new ExceptionRule());
    }

    public void process(AllLines allLines) {
        for (LineRule lineRule : lineRules)
            lineRule.process(allLines);
        log.info("rules done: " + allLines.file());
    }
}
