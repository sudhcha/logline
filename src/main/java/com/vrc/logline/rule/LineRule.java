package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;
import java.util.Set;

public interface LineRule {

    void apply(String file, List<String> inputLines, Set<Line> outputLines, RulePackage rulePackage);

    void end();

    void start();
}
