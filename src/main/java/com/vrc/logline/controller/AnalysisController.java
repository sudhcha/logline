package com.vrc.logline.controller;

import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class AnalysisController extends BaseController implements Controller {

    private static final Logger log = Logger.getLogger(AnalysisController.class);

    public AnalysisController() {
        super("analyse");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        setHeaders(response);
        Map<String, Object> model = new HashMap<String, Object>();
        renderer.render("results", model, response);
        log.info("[" + request.getPath() + "|" + request.getRequestTime() + "]processed");
    }
}
