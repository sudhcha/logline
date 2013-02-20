package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import com.vrc.logline.repository.AllLines;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ExceptionRule extends BaseRule {

    private Pattern pattern;

    public ExceptionRule() {
        this.pattern = Pattern.compile("(?i)exception");
    }

    @Override
    protected String name() {
        return "ExceptionRule";
    }

    @Override
    public void process(AllLines allLines) {
        for (String processedLine : allLines.processedLines()) {
            if (!pattern.matcher(processedLine).find()) continue;
            Line errorLine = new Line(processedLine).ofFile(allLines.file()).markError();
            allLines.addErrorLine(errorLine);
        }
    }
}
