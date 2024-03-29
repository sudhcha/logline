package com.vrc.logline.domain;

import com.vrc.logline.repository.AllControllers;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public class HttpTask implements Runnable {
    private static final Logger log = Logger.getLogger(HttpTask.class);

    private final Request request;
    private final Response response;
    private AllControllers allControllers = new AllControllers();

    public HttpTask(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void run() {
        try {
            allControllers.act(request, response);
        } catch (Exception e) {
            log.error(request.getPath(), e);
        }
    }
}

