package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.Set;
import java.util.regex.Pattern;

public class ExceptionRule implements LineRule {

    @Override
    public void apply(String inputLine, RulePackage rulePackage, Set<Line> outputLines, String file) {
        if (Pattern.compile("(?i)exception").matcher(inputLine).find()){
            Line outputLine = new Line(inputLine).ofFile(file).markError();
            if(!outputLines.contains(outputLine))
                outputLines.add(outputLine);
        }
    }
}
