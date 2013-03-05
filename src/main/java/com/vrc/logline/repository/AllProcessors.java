package com.vrc.logline.repository;

import com.vrc.logline.processor.ExceptionProcessor;
import com.vrc.logline.processor.Processor;
import com.vrc.logline.processor.TimelineProcessor;
import com.vrc.logline.processor.XMLProcessor;
import difflib.StringUtills;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AllProcessors {
    private static final Logger log = Logger.getLogger(AllProcessors.class);
    private List<Processor> processors = new ArrayList<Processor>();

    public AllProcessors(String startDate, String endDate) {
        this.processors.add(new XMLProcessor());
        this.processors.add(new ExceptionProcessor());
        if (StringUtils.isNotBlank(startDate))
            this.processors.add(new TimelineProcessor(startDate, endDate));
    }

    public void process(AllLines allLines) {
        for (Processor processor : processors)
            processor.process(allLines);
        log.info("processing done: " + allLines.file());
    }

}
