package com.vrc.logline.repository;

import com.vrc.logline.domain.Line;
import com.vrc.logline.rule.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AllRules {
    private static final Logger log = Logger.getLogger(AllRules.class);

    private List<LineRule> lineRules = new ArrayList<LineRule>();
    private List<String> keys;

    public AllRules(List<String> keys) {
        this.keys = keys;
        this.lineRules.add(new KeyRule(keys));
//        this.lineRules.add(new ThreadRule());
        this.lineRules.add(new ExceptionRule());
    }

    public void apply(File file, Set<Line> outputLines) throws Exception {
        log.info("processing " + file.getName() + " ...");
        RulePackage rulePackage = new RulePackage().addKeys(keys);
        for (LineRule lineRule : lineRules) {
            List<String> inputLines = FileUtils.readLines(file);
            lineRule.start();
            lineRule.apply(file.getName(), inputLines, outputLines, rulePackage);
            lineRule.end();
        }
    }
}
