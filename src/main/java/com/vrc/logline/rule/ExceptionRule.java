package com.vrc.logline.rule;

import com.vrc.logline.domain.Config;
import com.vrc.logline.domain.Line;
import com.vrc.logline.repository.AllLines;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionRule extends BaseRule {

    private Config config = Config.get();

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

            Matcher matcher = config.datePattern1().matcher(title);
            if (matcher.find()) {
                timeStamp = matcher.group("timestamp");
            } else {
                matcher = config.datePattern2().matcher(title);
                if (matcher.find())
                    timeStamp = matcher.group();
            }
            Line errorLine = new Line(processedLine).ofFile(allLines.file()).markError();
            errorLine.markTime(timeStamp);
            errorLine.markErrorTitle(title.replaceAll("\\[.*\\]", "").replaceAll(timeStamp, ""));
            allLines.addErrorLine(errorLine);
        }
    }
}
