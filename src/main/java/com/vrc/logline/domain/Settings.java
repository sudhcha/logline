package com.vrc.logline.domain;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private Map<String, String> strips;

    public static final String THREAD_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2},[0-9]{0,3}]\\s*(?<thread>.*)\\s*(INFO|WARN|FATAL|ERROR|DEBUG)";
    public static final Integer PORT = 8080;
    public static int context = 20;

    public Settings() {
        this.strips = new HashMap<String, String>();
        strips.put("WebContainer\\s*:", "WC");
        strips.put("com.bcbsa.blue2.", "B2.");
        strips.put("com.bcbsa.csdp.", "B2.");
        strips.put("(INFO|ERROR|WARN|FATAL)", "");
        strips.put("MessageListenerThreadPool\\s*:", "MLP");
        strips.put("[[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}\\s*[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}:.*]\\s*.*\\s*SystemOut\\s*O", " ");
    }

    public Map<String, String> shorts() {
        return strips;
    }
}
