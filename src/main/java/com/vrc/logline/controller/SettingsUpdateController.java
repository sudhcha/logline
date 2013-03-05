package com.vrc.logline.controller;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class SettingsUpdateController extends BaseController {

    public SettingsUpdateController() {
        super("settings-update");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        String content = request.getParameter("content");
        config.reload(content);
        Map<String, Object> model = new HashMap<String, Object>();
        renderer.render("settings-update-results", model, response);
    }
}
