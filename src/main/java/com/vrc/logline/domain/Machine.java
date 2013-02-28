package com.vrc.logline.domain;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Machine {
    private static final Logger log = Logger.getLogger(Machine.class);
    private Config config;
    private String name;
    private String logDir;
    private String configDir;

    public Machine(String name) {
        this.name = name;
        this.config = Config.get();
    }

    public List<String> getLogFiles(String type) throws Exception {
        String target = config.userDir() + "/logs/";
        download(logDir, target, type);
        return Arrays.asList(new File(target).list());
    }

    public List<String> getConfigFiles() throws Exception {
        String target = config.userDir() + "/config/";
        download(configDir, target, null);
        return Arrays.asList(new File(target).list());
    }

    public Machine download(String source, String target, String type) throws Exception {
        Pattern pattern = pattern(type);
        clean(target);
        FTPClient ftpClient = ftpClient();

        for (FTPFile ftpFile : ftpClient.listFiles(source)) {
            String fileName = ftpFile.getName();
            if (!pattern.matcher(fileName).find()) continue;
            FileOutputStream fos = new FileOutputStream(target + fileName);
            ftpClient.retrieveFile(source + fileName, fos);
            fos.close();
            log.info("downloaded " + fileName);
        }
        ftpClient.disconnect();
        return this;
    }

    public boolean nameIs(String name) {
        return this.name.equals(name);
    }

    public String name() {
        return name;
    }

    private Pattern pattern(String type) {
        if (StringUtils.isBlank(type)) return Pattern.compile(".*");
        return Pattern.compile(type.replaceAll(",", "|"));
    }

    private void clean(String path) {
        File dir = new File(path);
        if (dir.exists())
            for (File file : dir.listFiles())
                file.delete();
        else
            dir.mkdir();
    }

    private FTPClient ftpClient() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(name);
        ftpClient.login(config.username(), config.password());
        return ftpClient;
    }

    public Machine withLogDir(String logDir) {
        this.logDir = logDir;
        return this;
    }

    public Machine withConfigDir(String configDir) {
        this.configDir = configDir;
        return this;
    }
}
                                                                              