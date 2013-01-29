package com.vrc.logline.domain;

import java.awt.*;
import java.net.URI;

public class Browser {
    private final String url = "http://localhost:8080/";

    public void start() throws Exception {
        Desktop.getDesktop().browse(URI.create(url));
    }
}
