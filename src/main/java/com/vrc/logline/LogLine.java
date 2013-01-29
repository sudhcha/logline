package com.vrc.logline;

import com.vrc.logline.domain.AppServer;
import com.vrc.logline.domain.Browser;

public class LogLine {

    public static void main(String[] list) throws Exception {
        new AppServer().start();
        new Browser().start();
    }
}
