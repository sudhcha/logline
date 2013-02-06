package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;

public class ThreadRule implements LineRule {

    @Override
    public void apply(String inputLine, RulePackage rulePackage, List<Line> outputLines) {
        for (String thread : rulePackage.threads())
            if (inputLine.contains(thread))
                outputLines.add(new Line(inputLine));
    }
}
