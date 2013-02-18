package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyRule extends BaseRule {
    private Pattern threadPattern;
    private Pattern keyPattern;

    public KeyRule(List<String> keys) {
        threadPattern = Pattern.compile("\\s*(?<thread>.*)\\s*(INFO|ERROR)");
        for (int i = 0; i < keys.size(); i++)
            keys.set(i, StringUtils.trimToEmpty(keys.get(i)));
        keyPattern = Pattern.compile("(?i)" + StringUtils.join(keys.toArray(), "|"));
    }

    @Override
    public void apply(String inputLine, RulePackage rulePackage, Set<Line> outputLines, String file) {
        if (!keyPattern.matcher(inputLine).find())
            return;
        Matcher matcher = threadPattern.matcher(inputLine);
        if (matcher != null && matcher.find()) {
            String thread = matcher.group("thread");
            rulePackage.addThread(thread);
            outputLines.add(new Line(inputLine).ofFile(file).ofThread(thread));
        } else {
            outputLines.add(new Line(inputLine).ofFile(file));
        }
    }

    @Override
    protected String name() {
        return "KeyRule";
    }
}
