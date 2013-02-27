package com.vrc.logline.controller;

import com.vrc.logline.domain.Settings;
import org.apache.commons.io.FileUtils;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsController extends BaseController {

    protected SettingsController() {
        super("settings");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        File file = new File(Settings.USER_DIR + "/config.properties");
        List<String> lines = FileUtils.readLines(file);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("configLines", lines);
        renderer.render("settings", model, response);
    }
}
