package com.vrc.logline.service;

import com.vrc.logline.domain.CVSRepo;
import com.vrc.logline.domain.FileDiff;
import com.vrc.logline.domain.Machine;
import com.vrc.logline.repository.AllMachines;

import java.util.ArrayList;
import java.util.List;

public class FileDiffService {
    private AllMachines allMachines = new AllMachines();

    public List<FileDiff> process(String machineName, String releaseName) throws Exception {
        Machine machine = allMachines.getFor(machineName);
        CVSRepo cvsRepo = new CVSRepo();
        List<FileDiff> fileDiffs = new ArrayList<FileDiff>();
        List<String> serverFiles = machine.getConfigFiles();

        for (String serverFile : serverFiles) {
            String cvsFile = cvsRepo.getFor(releaseName, machine.shortName(), serverFile);
            fileDiffs.add(new FileDiff(serverFile, serverFile, cvsFile));
        }
        System.out.println(fileDiffs);
        return fileDiffs;
    }
}
