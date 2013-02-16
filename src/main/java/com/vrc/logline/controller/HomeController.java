package com.vrc.logline.controller;

import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController extends BaseController {

    private static final Logger log = Logger.getLogger(HomeController.class);

    public HomeController() {
        super("");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        addHeaders(response);
        response.setValue("Content-Type", "text/html");
        Map<String, Object> model = new HashMap<String, Object>();
        renderer.render("home", model, response);
        log.info(request.getPath());
    }
}
