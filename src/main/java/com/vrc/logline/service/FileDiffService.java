package com.vrc.logline.service;

import com.vrc.logline.domain.CVSRepo;
import com.vrc.logline.domain.Config;
import com.vrc.logline.domain.FileDiff;
import com.vrc.logline.domain.Machine;
import com.vrc.logline.repository.AllMachines;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FileDiffService {
    private static final Logger log = Logger.getLogger(FileDiffService.class);
    private AllMachines allMachines = new AllMachines();

    private List<FileDiff> changedFileDiffs = new ArrayList<FileDiff>();
    private List<FileDiff> missingFileDiffs = new ArrayList<FileDiff>();

    public void process(String machineName, String releaseName) throws Exception {
        Machine machine = allMachines.getFor(machineName);
        CVSRepo cvsRepo = new CVSRepo();

        List<String> machineFiles = machine.getConfigFiles("home|scripts|.properties\\z|.xml\\z|.xsd\\z|.py\\z|.sh\\z|blue2");
        for (String machineFile : machineFiles) {
            String relativePath = machineFile.replace(Config.get().userDir() + "\\config\\", "");
            String cvsFile = cvsRepo.getFor(releaseName, machine.shortName(), relativePath);
            FileDiff fileDiff = new FileDiff(relativePath, machineFile, cvsFile).process();
            if (fileDiff.isMissing())
                missingFileDiffs.add(fileDiff);
            else
                changedFileDiffs.add(fileDiff);
        }
        log.info("completed processing");
    }

    public List<FileDiff> changedFileDiffs() {
        return changedFileDiffs;
    }

    public List<FileDiff> missingFileDiffs() {
        return missingFileDiffs;
    }
}

