package com.vrc.logline.domain;

import org.apache.log4j.Logger;

import java.awt.*;
import java.net.URI;

public class Browser {
    private static final Logger log = Logger.getLogger(Browser.class);
    private final String url = "http://localhost:8080/";

    public void start() throws Exception {
        Desktop.getDesktop().browse(URI.create(url));
        log.info("Browser opened");

    }
}
