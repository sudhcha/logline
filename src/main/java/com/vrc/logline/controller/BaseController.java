package com.vrc.logline.controller;

import org.simpleframework.http.Response;

public abstract class BaseController {

    protected void setHeaders(Response response) {
        long time = System.currentTimeMillis();
        response.setValue("Content-Type", "text/html");
        response.setValue("Server", "Logline-VRC");
        response.setDate("Date", time);
        response.setDate("Last-Modified", time);
    }
}
