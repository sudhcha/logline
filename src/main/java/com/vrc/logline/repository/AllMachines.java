package com.vrc.logline.repository;

import com.vrc.logline.domain.Machine;

import java.util.ArrayList;
import java.util.List;

public class AllMachines {

    private List<Machine> machines = new ArrayList<Machine>();

    public AllMachines() {
        machines.add(new Machine("nasnmasdev", "/was7blue2/logs", ""));
        machines.add(new Machine("nasnmasmo", "/was7blue2/logs", ""));
        machines.add(new Machine("nasnmas3", "/was7blue2/logs", ""));
        machines.add(new Machine("localhost", "/pub/", ""));
    }

    public Machine getFor(String environment) {
        for (Machine machine : machines)
            if (machine.nameIs(environment))
                return machine;
        return null;
    }
}
