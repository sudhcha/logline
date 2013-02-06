package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;

import java.util.List;
import java.util.regex.Pattern;

public class KeyRule implements LineRule {

    @Override
    public void apply(String inputLine, RulePackage rulePackage, List<Line> outputLines) {
        for (String key : rulePackage.keys()) {
            if (hasNot(inputLine, key)) continue;
            Line line = new Line(inputLine);
            //pick and add thread number to package
            outputLines.add(line);
        }
    }

    private boolean hasNot(String line, String key) {
        return !Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(line).find();
    }
}
