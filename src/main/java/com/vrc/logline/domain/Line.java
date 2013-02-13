package com.vrc.logline.domain;

import org.apache.commons.lang.StringEscapeUtils;

public class Line {

    private String file;
    private String value;
    private String thread;
    private boolean error;

    public Line(String value) {
        this.value = value;
    }

    public String html() {
        return StringEscapeUtils.escapeHtml(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isBig() {
        return value.length() > 1000;
    }

    public boolean isError() {
        return error;
    }

    public Line markError() {
        this.error = true;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        if (value != null ? !value.equals(line.value) : line.value != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    public Line ofThread(String thread) {
        this.thread = thread;
        return this;
    }

    public Line ofFile(String file) {
        this.file = file;
        return this;
    }

    public String getThread() {
        return thread;
    }
}
