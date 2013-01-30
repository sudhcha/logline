package com.vrc.logline;

import com.vrc.logline.domain.AppServer;
import com.vrc.logline.domain.Browser;

public class LogLine {

    public static void main(String[] list) throws Exception {
        final AppServer appServer = new AppServer();
        appServer.start();
        new Browser().start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    appServer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
