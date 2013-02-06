package com.vrc.logline.repository;

import com.vrc.logline.domain.Line;
import com.vrc.logline.rule.*;

import java.util.ArrayList;
import java.util.List;

public class AllRules {
    private List<LineRule> lineRules = new ArrayList<LineRule>();
    private List<String> keys;

    public AllRules(List<String> keys) {
        this.keys = keys;
        this.lineRules.add(new KeyRule());
        this.lineRules.add(new ThreadRule());
        this.lineRules.add(new ExceptionRule());
    }

    public void apply(String inputLine, List<Line> outputLines){
        RulePackage rulePackage = new RulePackage().addKeys(keys);
        for (LineRule lineRule : lineRules)
           lineRule.apply(inputLine, rulePackage, outputLines);
    }
}
