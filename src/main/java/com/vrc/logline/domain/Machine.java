package com.vrc.logline.domain;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Machine {
    private String name;
    private Map<String,String> locations;

    public Machine(String name) {
        this.name = name;
        this.locations = new HashMap<>();
    }

    public boolean nameIs(String name) {
        return this.name.equals(name);
    }

    public String name() {
        return name;
    }
    
    public Machine add(String key, String path){
        locations.put(key,path);
        return this;
    }

    public String getLocation(String key) {
        return locations.get(key);
    }
}
                                                                              