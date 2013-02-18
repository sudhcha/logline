package com.vrc.logline;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.StopWatch;
import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import org.grep4j.core.result.GrepResults;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertTrue;
import static org.grep4j.core.Grep4j.constantExpression;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.fluent.Dictionary.on;

public class LogLineTest {

    @Test
    public void shouldMatchDatePattern1AndPickThreadName() throws ParseException {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2},[0-9]{0,3}]\\s*(?<thread>.*)\\s*INFO");
        Matcher matcher = pattern.matcher("[2013-02-04 09:57:58,574] [WebContainer : 3538] INFO  com.xxx.xxx.web.action.SFClaimSummaryAction - UI sccfNum input:2345343");
        assertTrue(matcher != null && matcher.find());
        System.out.println(matcher.toMatchResult().toString() + "---" + matcher.group("thread"));
    }

    @Test
    public void shouldMatchDatePattern2() throws ParseException {
        Pattern pattern = Pattern.compile("[0-9]{2}\\s*(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s*[0-9]{4}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2}");
        Matcher matcher = pattern.matcher("04 Feb 2013 09:55:10  INFO JaxmRouterManager:64 - authority is matching.");
        assertTrue(matcher != null && matcher.find());
        System.out.println(matcher.toMatchResult().toString());
    }

    @Test
    public void shouldMatchLogPattern3() throws ParseException {
        String regex = "[[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}\\s*[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}:.*]\\s*.*\\s*SystemOut\\s*O";
        String sample = "[1/26/13 8:14:20:706 EST] 00010113 SystemOut     O [2013-01-26 08:14:20,706] [WebContainer : 64071] INFO  com.bcbsa.blue2.web.action.SccfSearchAction - DisplayList.size() 4".replaceAll(regex, "XX");
        System.out.println(sample);

        String result = "1104 [pool-1-thread-8] INFO  com.vrc.logline.controller.HomeController".replaceAll("(INFO|ERROR)", "XX");
        System.out.println(result);

        Pattern pattern = Pattern.compile("<\\?xml\\s+");
        Matcher matcher = pattern.matcher("[2013-01-23 10:43:07,544] [WebContainer : 23606] INFO  com.bcbsa.blue2.web.action.SccfSearchAction - SDO Output <?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        assertTrue(matcher != null && matcher.find());
        System.out.println(matcher.toMatchResult().toString());
    }

    @Test
    public void shouldUseGrep4J() {
        Profile localProfile = ProfileBuilder.newBuilder()
                .name("Local server log")
                .filePath("C:/Work/test/b2_node1.log.2")
                .onLocalhost()
                .build();

        GrepResults results = grep(constantExpression("30320123170618600"), on(localProfile));
        System.out.println("Grep results : " + results);
        System.out.println("Total lines found : " + results.totalLines());
        System.out.println("Total Execution Time : " + results.getTotalExecutionTime());
    }

    @Test
    public void shouldTestPerformance() throws IOException {
        File dir = new File("C:\\Users\\vchakrav\\Documents\\b2-errors\\prod-abends");
        List<String> lines = new ArrayList<String>();
        Matcher matcher = null;
        Pattern pattern = Pattern.compile("74820130210002100|FZCTF2");
        StopWatch watch = new StopWatch();
        watch.start();
        for (File file : dir.listFiles()) {
            if(file.isDirectory()) continue;
            System.out.println("processing "+file.getName());
            String fileContent = FileUtils.readFileToString(file);
            matcher = pattern.matcher(fileContent);
            while (matcher.find()) {
                lines.add(matcher.toMatchResult().toString());
            }
        }
        watch.stop();
        System.out.println("Matched " + lines.size() + "|" + watch);

        watch.reset();
        List<String> lines2 = new ArrayList<String>();
        watch.start();
        for (File file : dir.listFiles()) {
            if(file.isDirectory()) continue;
            System.out.println("processing "+file.getName());
            List<String> fileLines = FileUtils.readLines(file);
            for (String fileLine : fileLines) {
                matcher = pattern.matcher(fileLine);
                if (matcher.find()) {
                    lines2.add(matcher.toMatchResult().toString());
                }
            }
        }
        watch.stop();
        System.out.println("Matched " + lines2.size() + "|" + watch);
        watch.reset();
    }


}
