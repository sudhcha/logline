package com.vrc.logline.rule;

import com.vrc.logline.domain.Line;
import com.vrc.logline.domain.Settings;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class    XMLRule extends BaseRule {

    private Pattern pattern = Pattern.compile("(:?<.+>)");
    private Pattern startPattern = Pattern.compile("<\\?xml\\s+");
    private Pattern threadPattern = Pattern.compile(Settings.THREAD_REGEX);
    private final String xmlStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    @Override
    public void apply(String file, List<String> inputLines, Set<Line> outputLines, RulePackage rulePackage) {
        StringBuffer xmlString = null;
        String thread = null;
        boolean inXml = false;

        for (String inputLine : inputLines) {
            if (startPattern.matcher(inputLine).find()) {
                xmlString = new StringBuffer().append(inputLine);
                Matcher matcher = threadPattern.matcher(inputLine);
                thread = (matcher != null && matcher.find()) ? matcher.group("thread") : null;
                inXml = true;
                continue;
            }
            if (inXml) {
                if (pattern.matcher(inputLine).find()){
                    xmlString.append(inputLine);
                }
                else {
                    String xml = xmlString.toString().replace(xmlStart, "[XML]") + "[/XML]";
                    System.out.println(xml);
                    outputLines.add(new Line(xml).ofThread(thread).ofFile(file));
                    inXml = false;
                }
            }
        }
    }

    @Override
    protected String name() {
        return "XMLRule";
    }
}
