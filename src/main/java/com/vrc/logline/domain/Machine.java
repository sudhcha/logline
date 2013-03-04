package com.vrc.logline.domain;

import com.vrc.logline.remote.MyFile;
import com.vrc.logline.remote.MyFtp;
import com.vrc.logline.remote.MyJsch;
import com.vrc.logline.remote.MyRemote;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.regex.Pattern;

public class Machine {
    private static final Logger log = Logger.getLogger(Machine.class);

    private String name;
    private String shortName;
    private String logDir;
    private String configDir;

    private Config config;
    private Pattern logPattern = Pattern.compile("[\\w]+.log(.\\d)*");

    public Machine(String name) {
        this.name = name;
        this.config = Config.get();
    }

    public List<String> browseLogFiles() throws Exception {
        return remote().browse(logDir, logPattern);
    }

    public List<String> getLogFiles(String type) throws Exception {
        String targetDir = config.userDir() + "/logs/";
        remote().download(logDir, targetDir, false, pattern(type));
        log.info("all log files downloaded");
        return new MyFile(targetDir).getChildren();
    }

    public List<String> getConfigFiles(String type) throws Exception {
        String targetDir = config.userDir() + "/config/";
        remote().download(configDir, targetDir, true, pattern(type));
        log.info("all config files downloaded");
        return new MyFile(targetDir).getChildren();
    }

    public boolean nameIs(String name) {
        return this.name.equals(name) || this.shortName.equals(name);
    }

    public String name() {
        return name;
    }

    public String shortName() {
        return shortName;
    }

    public Machine withLogDir(String logDir) {
        this.logDir = logDir;
        return this;
    }

    public Machine withConfigDir(String configDir) {
        this.configDir = configDir;
        return this;
    }

    public Machine withShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    private Pattern pattern(String type) {
        if (StringUtils.isBlank(type)) return Pattern.compile(".*");
        return Pattern.compile(type.replaceAll(",", "|"));
    }

    private MyRemote remote() {
        return new MyJsch(name);
    }
}
                                                                              