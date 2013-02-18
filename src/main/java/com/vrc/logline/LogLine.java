package com.vrc.logline;

import com.vrc.logline.domain.AppServer;
import com.vrc.logline.domain.Browser;
import org.apache.log4j.Logger;
import org.grep4j.core.model.Profile;
import org.grep4j.core.model.ProfileBuilder;
import org.grep4j.core.result.GrepResults;

import static org.grep4j.core.Grep4j.constantExpression;
import static org.grep4j.core.Grep4j.grep;
import static org.grep4j.core.fluent.Dictionary.on;

public class LogLine {
    private static final Logger log = Logger.getLogger(LogLine.class);

    public static void main(String[] list) throws Exception {
        final AppServer appServer = new AppServer();
        appServer.start();
        //new Browser().start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    appServer.stop();
                } catch (Exception e) {
                    log.error(e);
                }
            }
        }));

    }
}
