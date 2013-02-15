package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import java.util.Set;
import java.util.regex.Pattern;

public class ExceptionRule extends BaseRule {

    private Pattern pattern;

    public ExceptionRule() {
        this.pattern = Pattern.compile("(?i)exception");
    }

    @Override
    public void apply(String inputLine, RulePackage rulePackage, Set<Line> outputLines, String file) {
        if (!pattern.matcher(inputLine).find())
            return;
        Line outputLine = new Line(inputLine).ofFile(file).markError();
        if (!outputLines.contains(outputLine))
            outputLines.add(outputLine);
    }

    @Override
    protected String name() {
        return "ExceptionRule";
    }
}
