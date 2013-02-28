package com.vrc.logline.service;

import com.vrc.logline.domain.Config;
import com.vrc.logline.domain.Machine;
import com.vrc.logline.repository.AllMachines;

import java.util.List;

public class LogFetchService {

    private AllMachines allMachines = new AllMachines();

    public LogFetchService() {
        this.allMachines = new AllMachines();
    }

    public List<String> getFiles(String machineName) throws Exception {
        Machine machine = allMachines.getFor(machineName);
        return machine.getLogFiles("log");
    }


}
