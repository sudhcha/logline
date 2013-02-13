package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.Set;

public class ThreadRule implements LineRule {

    @Override
    public void apply(String inputLine, RulePackage rulePackage, Set<Line> outputLines, String file) {
        for (String thread : rulePackage.threads())
            if (inputLine.contains(thread))
                outputLines.add(new Line(inputLine).ofThread(thread).ofFile(file));
    }
}
