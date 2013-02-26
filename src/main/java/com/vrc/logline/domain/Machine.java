package com.vrc.logline.domain;

import java.net.InetAddress;

public class Machine {
    private String name;
    private String logsDirectory;
    private String configDirectory;

    public Machine(String name, String logsDirectory, String configDirectory) {
        this.name = name;
        this.logsDirectory = logsDirectory;
        this.configDirectory = configDirectory;
    }

    public boolean nameIs(String name) {
        return this.name.equals(name);
    }

    public String logsDirectory() {
        return logsDirectory;
    }

    public String configDirectory() {
        return configDirectory;
    }

    public String name() {
        return name;
    }
}
