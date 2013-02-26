package com.vrc.logline.domain;

import com.vrc.logline.repository.AllMachines;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LogFiles {
    private static final Logger log = Logger.getLogger(LogFiles.class);
    private Pattern pattern;
    private String environment;
    private AllMachines allMachines;

    public LogFiles(String environment) {
        this.environment = environment;
        this.pattern = Pattern.compile("node1|postprocess");
        this.allMachines = new AllMachines();
    }

    public List<String> pull() throws Exception {
        List<String> fileNames = new ArrayList<String>();
        Machine machine = allMachines.getFor(environment);

        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(machine.name());
        ftpClient.login(Settings.USERNAME, Settings.PASSWORD);

        for (FTPFile ftpFile : ftpClient.listFiles(machine.logsDirectory())) {
            String ftpFileName = ftpFile.getName();
            if (!pattern.matcher(ftpFileName).find()) continue;

            FileOutputStream fos = new FileOutputStream(Settings.DIRECTORY + "/" + ftpFileName);
            ftpClient.retrieveFile(machine.logsDirectory() + ftpFileName, fos);
            fos.close();

            log.info("downloaded " + ftpFileName);
            fileNames.add(ftpFileName);
        }
        ftpClient.disconnect();
        return fileNames;
    }
}
