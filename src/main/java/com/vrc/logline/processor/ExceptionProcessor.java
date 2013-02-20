package com.vrc.logline.processor;

import com.vrc.logline.domain.Settings;
import com.vrc.logline.repository.AllLines;

import java.util.regex.Pattern;

public class ExceptionProcessor implements Processor {

    private Pattern startPattern = Pattern.compile("(?i)exception");
    private Pattern pattern = Pattern.compile("\\s*at\\s+");
    private Pattern datePattern1 = Pattern.compile(Settings.DATE_REGEX1);
    private Pattern datePattern2 = Pattern.compile(Settings.DATE_REGEX2);

    @Override
    public void process(AllLines allLines) {
        StringBuffer errorString = null;
        boolean inStack = false;

        for (String fileLine : allLines.fileLines()) {
            if (startPattern.matcher(fileLine).find() && !inStack) {
                errorString = new StringBuffer().append(fileLine);
                inStack = true;
                continue;
            }
            if (inStack) {
                if (pattern.matcher(fileLine).find() || !(datePattern1.matcher(fileLine).find() || datePattern2.matcher(fileLine).find())) {
                    errorString.append(fileLine);
                } else {
                    allLines.addProcessedLine("[ERROR]" + errorString.toString() + "[/ERROR]");
                    inStack = false;
                }
            }
        }
    }
}
