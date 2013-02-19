package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ThreadRule extends BaseRule {

    private Pattern xmlStartPattern = Pattern.compile("<\\?xml\\s+");

    @Override
    public void apply(String file, List<String> inputLines, Set<Line> outputLines, RulePackage rulePackage) {
        for (String inputLine : inputLines) {
            for (String thread : rulePackage.threads())
                if (inputLine.contains(thread))
                    outputLines.add(new Line(inputLine).ofThread(thread).ofFile(file));
        }
    }

    @Override
    protected String name() {
        return "ThreadRule";
    }
}
