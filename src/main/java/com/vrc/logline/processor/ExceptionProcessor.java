package com.vrc.logline.processor;

import com.vrc.logline.domain.Settings;
import com.vrc.logline.repository.AllLines;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ExceptionProcessor implements Processor {

    private Pattern startPattern = Pattern.compile("(?i)exception");
    private Pattern pattern = Pattern.compile("\\s*at\\s+");
    private Pattern datePattern1 = Pattern.compile(Settings.DATE_REGEX1);
    private Pattern datePattern2 = Pattern.compile(Settings.DATE_REGEX2);
    private Pattern invalidPattern;

    public ExceptionProcessor() {
        invalidPattern = Pattern.compile("b2config.ConfigReader" +
                "|ejb.ExceptionProcessorStartUpBeanBean" +
                "|BatchResendConfigurationReader " +
                "|exception.processor.ejb.ExceptionProcessorBean" +
                "|dao.util.ReportQueryReader" +
                "|Claim type cannot be determined for SCCF" +
                "|There is no data to display for this tab" +
                "|No matching records found|(:?<.+>)");
    }

    @Override
    public void process(AllLines allLines) {
        StringBuffer errorString = null;
        boolean inStack = false;

        for (String fileLine : allLines.fileLines()) {
            if (startPattern.matcher(fileLine).find() && !inStack && !invalidPattern.matcher(fileLine).find()) {
                errorString = new StringBuffer().append(fileLine);
                inStack = true;
                continue;
            }
            if (inStack) {
                if (pattern.matcher(fileLine).find() || !(datePattern1.matcher(fileLine).find() || datePattern2.matcher(fileLine).find())) {
                    errorString.append(fileLine+"\n");
                } else {
                    allLines.addProcessedLine("[ERROR]" + errorString.toString() + "[/ERROR]");
                    inStack = false;
                }
            }
        }
    }

}
