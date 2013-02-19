package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyRule extends BaseRule {
    private Pattern threadPattern;
    private Pattern keyPattern;
    private Integer contextSize;

    public KeyRule(List<String> keys) {
        threadPattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2},[0-9]{0,3}\\s*(?<thread>.*)\\s*(INFO|ERROR)");
        for (int i = 0; i < keys.size(); i++)
            keys.set(i, StringUtils.trimToEmpty(keys.get(i)));
        keyPattern = Pattern.compile("(?i)" + StringUtils.join(keys.toArray(), "|"));
        this.contextSize = 20;
    }

    @Override
    public void apply(String file, List<String> inputLines, Set<Line> outputLines, RulePackage rulePackage) {
        for (int i = 0; i < inputLines.size(); i++) {
            String inputLine = inputLines.get(i);
            if (!keyPattern.matcher(inputLine).find())
                continue;

            Matcher matcher = threadPattern.matcher(inputLine);
            if (matcher != null && matcher.find()) {
                String thread = matcher.group("thread");
                rulePackage.addThread(thread);
                outputLines.add(new Line(inputLine).ofFile(file).ofThread(thread));
                for (int j = i + contextSize; j != i && j < inputLines.size(); j--)
                    outputLines.add(new Line(inputLines.get(j)).ofFile(file).ofThread(thread));
                for (int k = i - contextSize; k != i && k < 0; k++)
                    outputLines.add(new Line(inputLines.get(k)).ofFile(file).ofThread(thread));
            } else {
                outputLines.add(new Line(inputLine).ofFile(file));
                for (int j = i + contextSize; j != i && j < inputLines.size(); j--)
                    outputLines.add(new Line(inputLines.get(j)).ofFile(file));
                for (int k = i - contextSize; k != i && k < 0; k++)
                    outputLines.add(new Line(inputLines.get(k)).ofFile(file));
            }
        }
    }

    @Override
    protected String name() {
        return "KeyRule";
    }
}
