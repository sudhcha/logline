package com.vrc.logline.processor;

import com.vrc.logline.repository.AllLines;

public interface Processor {
    void process(AllLines inputLines);
}
