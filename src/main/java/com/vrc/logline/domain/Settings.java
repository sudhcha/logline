package com.vrc.logline.domain;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private Map<String, String> shorts;

    public Settings() {
        this.shorts = new HashMap<String, String>();
        shorts.put("WebContainer\\s*:", "WC");
        shorts.put("com.bcbsa.blue2.", "B2.");
        shorts.put("com.bcbsa.csdp.", "B2.");
        shorts.put("(INFO|ERROR)", "");
        shorts.put("MessageListenerThreadPool\\s*:", "MLP");
        shorts.put("[[0-9]{1,2}/[0-9]{1,2}/[0-9]{2,4}\\s*[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}:.*]\\s*.*\\s*SystemOut\\s*O", " ");
    }

    public Map<String, String> shorts() {
        return shorts;
    }
}
