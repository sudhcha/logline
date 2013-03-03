package com.vrc.logline.domain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.Properties;
import java.util.regex.Pattern;

public class Config {
    private static Config instance = new Config();
    private Integer port;
    private Integer context;
    private Boolean runMode;
    private String username;
    private String password;
    private String userDir;
    private String userCvsDir;
    private String devLogDir;
    private String devConfigDir;
    private String moLogDir;
    private String moConfigDir;
    private String prodLogDir;
    private String prodConfigDir;
    private Pattern datePattern1;
    private Pattern datePattern2;

    private Config() {
        Properties properties = new Properties();
        userDir = System.getProperty("user.dir");
        File file = new File(userDir + "/config.properties");
        if (!file.exists())
            file = new File(ClassLoader.getSystemResource("config.properties").getFile());
        try {
            properties.load(new FileReader(file));
            port = Integer.parseInt((String) properties.get("app.run.port"));
            runMode = (properties.get("app.run.mode")).equals("jar");
            context = Integer.parseInt((String) properties.get("log.context"));
            username = (String) properties.get("user.name");
            password = (String) properties.get("user.password");

            userCvsDir = (String) properties.get("user.cvs.dir");
            devLogDir = (String) properties.get("dev.log.dir");
            devConfigDir = (String) properties.get("dev.config.dir");
            moLogDir = (String) properties.get("mo.log.dir");
            moConfigDir = (String) properties.get("mo.config.dir");
            prodLogDir = (String) properties.get("prod.log.dir");
            prodConfigDir = (String) properties.get("prod.config.dir");

            datePattern1 = Pattern.compile("(?<timestamp>[0-9]{4}-[0-9]{2}-[0-9]{2}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2},[0-9]{0,3})]\\s*(?<thread>.*)\\s*(INFO|WARN|FATAL|ERROR|DEBUG)");
            datePattern2 = Pattern.compile("[0-9]{2}\\s*(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)\\s*[0-9]{4}\\s*[0-9]{1,2}:[0-9]{2}:[0-9]{2}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Config get() {
        return instance;
    }

    public Integer port() {
        return port;
    }

    public Integer context() {
        return context;
    }

    public Boolean runMode() {
        return runMode;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public String userDir() {
        return userDir;
    }

    public String userCvsDir() {
        return userCvsDir;
    }

    public String devLogDir() {
        return devLogDir;
    }

    public String devConfigDir() {
        return devConfigDir;
    }

    public String moLogDir() {
        return moLogDir;
    }

    public String moConfigDir() {
        return moConfigDir;
    }

    public String prodLogDir() {
        return prodLogDir;
    }

    public String prodConfigDir() {
        return prodConfigDir;
    }

    public String getPath(String path) {
        return ClassLoader.getSystemResource(path).getFile();
    }

    public Pattern datePattern1() {
        return datePattern1;
    }

    public Pattern datePattern2() {
        return datePattern2;
    }

    @Override
    public String toString() {
        return "Config[" +
                "port=" + port + "\n" +
                ", context=" + context + "\n" +
                ", runMode=" + runMode + "\n" +
                ", username='" + username + "\n" +
                ", password='" + password + "\n" +
                ", userDir='" + userDir + "\n" +
                ", userCvsDir='" + userCvsDir + "\n" +
                ", devLogDir='" + devLogDir + "\n" +
                ", devConfigDir='" + devConfigDir + "\n" +
                ", moLogDir='" + moLogDir + "\n" +
                ", moConfigDir='" + moConfigDir + "\n" +
                ", prodLogDir='" + prodLogDir + "\n" +
                ", prodConfigDir='" + prodConfigDir +
                ']';
    }
}
