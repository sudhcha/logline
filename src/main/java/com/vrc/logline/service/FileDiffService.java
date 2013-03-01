package com.vrc.logline.service;

import com.vrc.logline.domain.Config;
import com.vrc.logline.domain.FileDiff;
import com.vrc.logline.domain.Machine;
import com.vrc.logline.repository.AllMachines;

import java.util.ArrayList;
import java.util.List;

public class FileDiffService {
    private AllMachines allMachines = new AllMachines();
    private Config config = Config.get();

    public List<FileDiff> process(String machineName, String releaseName) throws Exception {
        List<FileDiff> fileDiffs = new ArrayList<FileDiff>();
        Machine machine = allMachines.getFor(machineName);
        List<String> machineFiles = machine.getConfigFiles();




        return fileDiffs;
    }
}
