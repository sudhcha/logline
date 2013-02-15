package com.vrc.logline.rule;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

public abstract class BaseRule implements LineRule {
    private static final Logger log = Logger.getLogger(ExceptionRule.class);

    private StopWatch watch = new StopWatch();

    @Override
    public void start() {
        watch.start();
    }

    @Override
    public void end() {
        watch.stop();
        log.info(name()+":" + watch);
        watch.reset();
    }

    protected abstract String name();
}

