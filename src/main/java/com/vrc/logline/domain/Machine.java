package com.vrc.logline.domain;

import com.vrc.logline.util.MyFileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.vrc.logline.util.MyFileUtil.getFilePaths;
import static com.vrc.logline.util.MyFileUtil.recreate;

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
        download(logDir, target, type, false);
        return getFilePaths(target);
    }

    public List<String> getConfigFiles() throws Exception {
        String target = config.userDir() + "/config/";
        download(configDir, target, null, true);
        return getFilePaths(target);
    }

    public Machine download(String source, String target, String type, Boolean recurse) throws Exception {
        Pattern pattern = pattern(type);
        FTPClient ftpClient = ftpClient();
        downloadRecurse(source, target, recurse, pattern, ftpClient);
        ftpClient.disconnect();
        return this;
    }

    private void downloadRecurse(String source, String target, Boolean recurse, Pattern pattern, FTPClient ftpClient) throws Exception {
        recreate(target);
        for (FTPFile ftpFile : ftpClient.listFiles(source)) {
            if (ftpFile.isDirectory() && recurse) {
                String sourceDir = source + "/" + ftpFile.getName();
                String targetDir = target + "/" + ftpFile.getName();
                downloadRecurse(sourceDir, targetDir, recurse, pattern, ftpClient);
                continue;
            }
            String fileName = ftpFile.getName();
            if (!pattern.matcher(fileName).find()) continue;
            FileOutputStream fos = new FileOutputStream(target + "/" + fileName);
            ftpClient.retrieveFile(source + fileName, fos);
            fos.close();
            log.info("downloaded " + fileName);
        }
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
                                                                              