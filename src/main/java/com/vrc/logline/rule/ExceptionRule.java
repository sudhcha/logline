package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;
import java.util.regex.Pattern;

public class ExceptionRule implements LineRule {

    @Override
    public void apply(String inputLine, RulePackage rulePackage, List<Line> outputLines) {
        if (Pattern.compile(Pattern.quote("exception"), Pattern.CASE_INSENSITIVE).matcher(inputLine).find()){
            Line outputLine = new Line(inputLine).markError();
            if(!outputLines.contains(outputLine))
                outputLines.add(outputLine);
        }
    }
}
