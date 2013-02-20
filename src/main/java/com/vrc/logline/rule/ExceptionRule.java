package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import com.vrc.logline.repository.AllLines;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ExceptionRule extends BaseRule {

    @Override
    protected String name() {
        return "ExceptionRule";
    }

    @Override
    public void process(AllLines allLines) {
        for (String processedLine : allLines.processedLines()) {
            if (!processedLine.contains("[/ERROR]")) continue;
            Line errorLine = new Line(processedLine).ofFile(allLines.file()).markError();
            allLines.addErrorLine(errorLine);
        }
    }
}
