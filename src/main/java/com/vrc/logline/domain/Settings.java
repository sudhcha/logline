package com.vrc.logline.domain;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Settings {
    private Map<String, String> strips;

    public static final String DATE_REGEX1 = "(?<timestamp>[0-9]{4}-[0-9]{2}-[0-9]{2}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2},[0-9]{0,3})]\\s*(?<thread>.*)\\s*(INFO|WARN|FATAL|ERROR|DEBUG)";
    public static final String DATE_REGEX2 = "[0-9]{2}\\s*(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s*[0-9]{4}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2}";
    public static Integer PORT;
    public static Integer CONTEXT;
    public static Boolean RUN_MODE;
    public static String USERNAME;
    public static String PASSWORD;
    public static String DIRECTORY;

    static {
        Properties config = new Properties();
        File file = new File(System.getProperty("user.dir") + "/config.properties");
        System.out.println(System.getProperty("user.dir"));
        if (!file.exists())
            file = new File(ClassLoader.getSystemResource("config.properties").getFile());
        try {
            config.load(new FileReader(file));
            PORT = Integer.parseInt((String) config.get("app.run.port"));
            RUN_MODE = (config.get("app.run.mode")).equals("jar");
            CONTEXT = Integer.parseInt((String) config.get("log.context"));
            USERNAME = (String) config.get("user.name");
            PASSWORD = (String) config.get("user.password");
            DIRECTORY = (String) config.get("user.local.directory");
            if (StringUtils.isBlank(DIRECTORY))
                DIRECTORY = System.getProperty("user.dir")+"/logs";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
