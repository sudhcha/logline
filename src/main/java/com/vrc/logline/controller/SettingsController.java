package com.vrc.logline.controller;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class SettingsController extends BaseController {

    public SettingsController() {
        super("settings-view");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("settings", config.read());
        renderer.render("settings", model, response);
    }
}
