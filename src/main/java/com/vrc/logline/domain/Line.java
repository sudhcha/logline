package com.vrc.logline.domain;

import org.apache.commons.lang.StringEscapeUtils;

public class Line {
    private String value;

    public Line(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return StringEscapeUtils.escapeHtml(value);
    }
}
