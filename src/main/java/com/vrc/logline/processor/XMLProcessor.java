package com.vrc.logline.processor;

import com.vrc.logline.repository.AllLines;

import java.util.regex.Pattern;

public class XMLProcessor implements Processor {
    private Pattern pattern = Pattern.compile("(:?<.+>)");
    private Pattern startPattern = Pattern.compile("<\\?xml\\s+");
    private final String xmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @Override
    public void process(AllLines allLines) {
        StringBuffer xmlString = null;
        boolean inXml = false;

        for (String fileLine : allLines.fileLines()) {
            if (startPattern.matcher(fileLine).find()) {
                xmlString = new StringBuffer().append(fileLine);
                inXml = true;
                continue;
            }
            if (inXml) {
                if (pattern.matcher(fileLine).find()) {
                    xmlString.append(fileLine);
                } else {
                    String xml = xmlString.toString().replace(xmlStart, "[XML]") + "[/XML]";
                    allLines.addProcessedLine(xml);
                    inXml = false;
                }
            } else {
                allLines.addProcessedLine(fileLine);
            }
        }
    }

}
