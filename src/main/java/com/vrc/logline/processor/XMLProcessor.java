package com.vrc.logline.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class XMLProcessor implements Processor {
    private Pattern pattern = Pattern.compile("(:?<.+>)");
    private Pattern startPattern = Pattern.compile("<\\?xml\\s+");
    private final String xmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @Override
    public List<String> apply(List<String> inputLines) {
        List<String> outputLines = new ArrayList<>();
        StringBuffer xmlString = null;
        boolean inXml = false;

        for (String inputLine : inputLines) {
            if (startPattern.matcher(inputLine).find()) {
                xmlString = new StringBuffer().append(inputLine);
                inXml = true;
                continue;
            }
            if (inXml) {
                if (pattern.matcher(inputLine).find()){
                    xmlString.append(inputLine);
                }
                else {
                    String xml = xmlString.toString().replace(xmlStart, "[XML]") + "[/XML]";
                    outputLines.add(xml);
                    inXml = false;
                }
            }else{
                outputLines.add(inputLine);
            }
        }
        return outputLines;
    }

}
