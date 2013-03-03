package com.vrc.logline.controller;

import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class LogToolController extends BaseController {
    private static final Logger log = Logger.getLogger(LogToolController.class);

    public LogToolController() {
        super("log-tool");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        addHeaders(response);
        response.setValue("Content-Type", "text/html");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("folder", config.userDir() + "\\logs");
        renderer.render("log-tool", model, response);
        log.info(request.getPath());
    }
}
