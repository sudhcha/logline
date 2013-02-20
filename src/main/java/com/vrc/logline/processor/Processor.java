package com.vrc.logline.processor;

import java.util.List;

public interface Processor {
    List<String> apply(List<String> inputLines);
}
