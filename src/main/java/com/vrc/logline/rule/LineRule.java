package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;

public interface LineRule {

    void apply(String inputLine, RulePackage rulePackage, List<Line> outputLines);
}
