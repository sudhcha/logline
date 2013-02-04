package com.vrc.logline.controller;

import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public class HomeController extends BaseController{

    private static final Logger log = Logger.getLogger(HomeController.class);

    public HomeController() {
        super("");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        setHeaders(response);
        renderer.render("home",null,response);
        log.info("[" + request.getPath() + "|" + request.getRequestTime() + "]processed");
    }
}
