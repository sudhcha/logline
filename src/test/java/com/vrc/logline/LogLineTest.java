package com.vrc.logline;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertTrue;

public class LogLineTest {

    @Test
    public void shouldMatchDatePattern1AndPickThreadName() throws ParseException {
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2},[0-9]{0,3}]\\s*(?<thread>.*)\\s*INFO");
        Matcher matcher = pattern.matcher("[2013-02-04 09:57:58,574] [WebContainer : 3538] INFO  com.xxx.xxx.web.action.SFClaimSummaryAction - UI sccfNum input:2345343");
        assertTrue(matcher != null && matcher.find());
        System.out.println(matcher.toMatchResult().toString()+"---"+matcher.group("thread"));
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
        String sample = "[1/26/13 8:14:20:706 EST] 00010113 SystemOut     O [2013-01-26 08:14:20,706] [WebContainer : 64071] INFO  com.bcbsa.blue2.web.action.SccfSearchAction - DisplayList.size() 4".replaceAll(regex,"XX");
        System.out.println(sample);

        String result = "1104 [pool-1-thread-8] INFO  com.vrc.logline.controller.HomeController".replaceAll("(INFO|ERROR)","XX");
        System.out.println(result);
    }
}
