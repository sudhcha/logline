package com.vrc.logline.domain;

import com.vrc.logline.repository.AllMachines;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LogFiles {
    private static final Logger log = Logger.getLogger(LogFiles.class);
    private Pattern pattern;
    private String machine;
    private AllMachines allMachines;

    public LogFiles(String machine) {
        this.machine = machine;
        this.pattern = Pattern.compile("log");
        this.allMachines = new AllMachines();
    }

    public List<String> pull() throws Exception {
        removeOldFiles();
        Machine machine = allMachines.getFor(this.machine);
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(machine.name());
        ftpClient.login(Settings.USERNAME, Settings.PASSWORD);

        List<String> fileNames = new ArrayList<String>();
        for (FTPFile ftpFile : ftpClient.listFiles(machine.getLocation("logs"))) {
            String ftpFileName = ftpFile.getName();
            if (!pattern.matcher(ftpFileName).find()) continue;
            FileOutputStream fos = new FileOutputStream(Settings.LOG_DIR + ftpFileName);
            ftpClient.retrieveFile(machine.getLocation("logs") + ftpFileName, fos);
            fos.close();
            log.info("downloaded " + ftpFileName);
            fileNames.add(ftpFileName);
        }
        ftpClient.disconnect();
        return fileNames;
    }

    private void removeOldFiles() {
        File logDir = new File(Settings.LOG_DIR);
        for (File file : logDir.listFiles())
            file.delete();
    }
}
