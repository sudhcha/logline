package com.vrc.logline.processor;

import com.vrc.logline.domain.Config;
import com.vrc.logline.repository.AllLines;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class TimelineProcessor implements Processor {
    private static final Logger log = Logger.getLogger(TimelineProcessor.class);

    private DateTime startDate;
    private DateTime endDate;
    private Config config;
    private final String pattern1 = "MM/dd/yyyy HH:mm:ss";
    private final String pattern2 = "yyyy-MM-dd HH:mm:ss,SSS";
    private final String pattern3 = "dd MMM yyyy HH:mm:ss";
    private final String pattern4 = "MM/dd/yyyy";

    public TimelineProcessor(String startDate, String endDate) {
        this.startDate = parse(startDate, pattern1);
        this.endDate = parse(endDate, pattern1);
        this.config = Config.get();
    }

    @Override
    public void process(AllLines allLines) {
        DateTime lineDate = null;
        List<String> processedLines = allLines.processedLines();
        List<String> filteredProcessedLines = new ArrayList<String>();
        for (String processedLine : processedLines) {
            lineDate = getLineDate(processedLine);
            if (lineDate != null && lineDate.isAfter(startDate) && lineDate.isBefore(endDate))
                filteredProcessedLines.add(processedLine);
        }
        log.info("Time Filter took " + filteredProcessedLines.size() + " out of " + processedLines.size());
        processedLines.clear();
        processedLines.addAll(filteredProcessedLines);
    }

    private DateTime getLineDate(String fileLine) {
        Matcher matcher = config.datePattern1().matcher(fileLine);
        if (matcher.find())
            return parse(matcher.group("timestamp"), pattern2);
        matcher = config.datePattern2().matcher(fileLine);
        if (matcher.find())
            return parse(matcher.group(), pattern3);
        return null;
    }

    private DateTime parse(String date, String pattern) {
        if (StringUtils.isBlank(date)) return DateTime.now();
        date = date.trim();
        return date.contains(":") ? DateTimeFormat.forPattern(pattern).parseDateTime(date)
                : DateTimeFormat.forPattern(pattern4).parseDateTime(date);
    }
}
