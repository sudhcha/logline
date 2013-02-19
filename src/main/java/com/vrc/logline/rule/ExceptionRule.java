package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ExceptionRule extends BaseRule {

    private Pattern pattern;

    public ExceptionRule() {
        this.pattern = Pattern.compile("(?i)exception");
    }

    @Override
    public void apply(String file, List<String> inputLines, Set<Line> outputLines, RulePackage rulePackage) {
        for (String inputLine : inputLines) {
            if (!pattern.matcher(inputLine).find()) continue;
            Line outputLine = new Line(inputLine).ofFile(file).markError();
            if (!outputLines.contains(outputLine))
                outputLines.add(outputLine);
        }
    }

    @Override
    protected String name() {
        return "ExceptionRule";
    }
}
