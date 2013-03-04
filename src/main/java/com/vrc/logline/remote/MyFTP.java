package com.vrc.logline.remote;

import com.vrc.logline.domain.Config;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class MyFtp implements MyRemote {

    private static final Logger log = Logger.getLogger(MyFtp.class);
    private String machine;
    private Config config;
    private FTPClient ftpClient;

    public MyFtp(String machine) {
        this.machine = machine;
        this.config = Config.get();
    }

    @Override
    public MyFtp connect() throws Exception {
        log.info("connecting to " + machine + " as " + config.username());
        ftpClient = new FTPClient();
        ftpClient.connect(machine);
        ftpClient.login(config.username(), config.password());
        log.info("connected to " + machine + " as " + config.username());
        return this;
    }

    @Override
    public List<String> browse(String sourcePath, Pattern pattern) throws Exception {
        connect();
        List<String> filteredFiles = new ArrayList<String>();
        for (FTPFile ftpFile : ftpClient.listFiles(sourcePath)) {
            if (!pattern.matcher(ftpFile.getName()).find()) continue;
            filteredFiles.add(ftpFile.getName() + "   [" + ftpFile.getTimestamp().getTime() + "]");
        }
        log.info("browsed [" + sourcePath + "] to give "+filteredFiles.size()+" files");
        return filteredFiles;
    }

    @Override
    public void download(String sourcePath, String targetPath, Boolean recurse, Pattern pattern) throws Exception {
        connect();
        new MyFile(targetPath).recreate();

        for (FTPFile ftpFile : ftpClient.listFiles(sourcePath)) {
            String fileName = ftpFile.getName();
            if (!pattern.matcher(fileName).find()) {
                continue;
            } else if (ftpFile.isDirectory() && recurse) {
                String sourceDir = sourcePath + "/" + ftpFile.getName();
                String targetDir = targetPath + "/" + ftpFile.getName();
                download(sourceDir, targetDir, recurse, pattern);
            } else {
                String targetFile = targetPath + "\\" + fileName;
                String sourceFile = sourcePath + "/" + fileName;
                log.info("downloading [" + sourceFile + "]");
                FileOutputStream targetStream = new FileOutputStream(new File(targetFile));
                ftpClient.retrieveFile(sourceFile, targetStream);
                targetStream.close();
                log.info("downloaded [" + targetFile + "]");
            }
        }
    }


}
