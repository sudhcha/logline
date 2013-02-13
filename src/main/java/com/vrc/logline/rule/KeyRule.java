package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import org.apache.log4j.Logger;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyRule implements LineRule {
    private static final Logger log = Logger.getLogger(KeyRule.class);

    @Override
    public void apply(String inputLine, RulePackage rulePackage, Set<Line> outputLines, String file) {
        for (String key : rulePackage.keys()) {
            if (hasNot(inputLine, key))
                continue;
            Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2},[0-9]{0,3}]\\s*(?<thread>.*)\\s*INFO");
            Matcher matcher = pattern.matcher(inputLine);
            if (matcher != null && matcher.find()) {
                String thread = matcher.group("thread");
                rulePackage.addThread(thread);
                outputLines.add(new Line(inputLine).ofFile(file).ofThread(thread));
            } else {
                outputLines.add(new Line(inputLine).ofFile(file));
            }
        }
    }

    private boolean hasNot(String line, String key) {
        return !Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(line).find();
    }
}
