package com.vrc.logline.domain;

import com.vrc.logline.controller.Controller;
import com.vrc.logline.controller.HomeController;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.PrintStream;

public class Task implements Runnable {
    private static final Logger log = Logger.getLogger(Task.class);

    private final Request request;
    private final Response response;

    public Task(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void run() {
        String reqStr = "[" + request.getPath() + "|" + request.getRequestTime() + "]";
        try {
            log.info(reqStr + "start");
            Controller controller = new HomeController();
            controller.act(request, response);
            log.info(reqStr + "end");
        } catch (Exception e) {
            log.error(reqStr + "error", e);
        }
    }
}

