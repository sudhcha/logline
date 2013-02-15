package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;
import java.util.Set;

public interface LineRule {

    void apply(String inputLine, RulePackage rulePackage, Set<Line> outputLines, String file);

    void end();

    void start();
}
