package com.vrc.logline.controller;

import com.vrc.logline.domain.Renderer;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public abstract class BaseController implements Controller{

    protected Renderer renderer = new Renderer("/views/");

    private String urlKey;

    protected BaseController(String urlKey) {
        this.urlKey = urlKey;
    }

    protected void addHeaders(Response response) {
        long time = System.currentTimeMillis();
        response.setValue("Content-Type", "text/html");
        response.setValue("Server", "Logline-VRC");
        response.setDate("Date", time);
        response.setDate("Last-Modified", time);
        response.setValue("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setValue("Cache-Control", "post-check=0, pre-check=0");
        response.setValue("Pragma", "no-cache");
    }

    @Override
    public boolean canTake(Request request) {
        return request.getPath().toString().contains(urlKey);
    }


}
