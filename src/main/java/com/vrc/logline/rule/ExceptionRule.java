package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import com.vrc.logline.domain.Settings;
import com.vrc.logline.repository.AllLines;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionRule extends BaseRule {

    private Pattern datePattern1 = Pattern.compile(Settings.DATE_REGEX1);
    private Pattern datePattern2 = Pattern.compile(Settings.DATE_REGEX2);

    @Override
    protected String name() {
        return "ExceptionRule";
    }

    @Override
    public void process(AllLines allLines) throws Exception {
        for (String processedLine : allLines.processedLines()) {
            if (!processedLine.contains("[/ERROR]"))
                continue;
            String title = StringUtils.substringBefore(processedLine, "\n");
            String timeStamp = "XXXX-XX-XX";

            Matcher matcher = datePattern1.matcher(title);
            if (matcher.find()) {
                timeStamp = matcher.group("timestamp");
            } else {
                matcher = datePattern2.matcher(title);
                if (matcher.find()) timeStamp = matcher.group();
            }
            Line errorLine = new Line(processedLine).ofFile(allLines.file()).markError();
            errorLine.markTime(timeStamp);
            errorLine.markErrorTitle(title.replaceAll("\\[.*\\]", ""));
            allLines.addErrorLine(errorLine);
        }
    }
}
