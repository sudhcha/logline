package com.vrc.logline.controller;

import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.PrintStream;

public class HomeController extends BaseController implements Controller {

    private static final Logger log = Logger.getLogger(HomeController.class);

    @Override
    public void act(Request request, Response response) throws Exception {
        setHeaders(response);
        PrintStream body = response.getPrintStream();
        body.append("<html><body>oh oh ooh</body></html>");
        body.close();
        log.info("processed request");
    }
}
