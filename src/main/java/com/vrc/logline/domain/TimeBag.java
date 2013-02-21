package com.vrc.logline.domain;

import org.apache.commons.lang.StringUtils;

public class TimeBag {

    private String headStamp;
    private String tailStamp;

    public void add(String stamp) {
        if (StringUtils.isBlank(this.headStamp) || this.headStamp.compareTo(stamp) > 1)   {
            this.headStamp = stamp;
            return;
        }
        if (StringUtils.isBlank(this.tailStamp) || this.tailStamp.compareTo(stamp) < 1)
            this.tailStamp = stamp;
    }

    @Override
    public String toString() {
        return "TimeBag[head=" + headStamp + "|tail=" + tailStamp + "]";
    }
}
