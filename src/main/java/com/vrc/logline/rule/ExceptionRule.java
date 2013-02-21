package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import com.vrc.logline.domain.Settings;
import com.vrc.logline.repository.AllLines;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionRule extends BaseRule {

    Pattern pattern1 = Pattern.compile(Settings.DATE_REGEX1);
    Pattern pattern2 = Pattern.compile(Settings.DATE_REGEX2);

    @Override
    protected String name() {
        return "ExceptionRule";
    }

    @Override
    public void process(AllLines allLines) {
        List<String> processedLines = allLines.processedLines();
        if (processedLines.isEmpty()) return;
        Collections.sort(processedLines);

        String startTime = (processedLines.get(0));
        String endTime = (processedLines.get(processedLines.size() - 1));

        for (String processedLine : processedLines) {
            if (!processedLine.contains("[/ERROR]")) continue;
            String lineTime = getTime(processedLine);
            if (lineTime.compareTo(startTime) < 0 || lineTime.compareTo(endTime) > 0) continue;
            Line errorLine = new Line(processedLine).ofFile(allLines.file()).markError();
            allLines.addErrorLine(errorLine);
        }
    }

    private String getTime(String processedLine) {
        Matcher matcher = pattern1.matcher(processedLine);
        if (matcher.find())
            return matcher.group("timestamp");
        else {
            matcher = pattern2.matcher(processedLine);
            if (matcher.find()) return matcher.group("timestamp");
        }
        return "";
    }
}
