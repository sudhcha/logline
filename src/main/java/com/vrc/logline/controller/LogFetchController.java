package com.vrc.logline.controller;

import com.vrc.logline.domain.LogFiles;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogFetchController extends BaseController {

    private static final Logger log = Logger.getLogger(LogFetchController.class);

    public LogFetchController() {
        super("ftp");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        log.info(request.getPath());
        addHeaders(response);
        String machine = request.getParameter("machine");
        LogFiles logFiles = new LogFiles(machine);
        List<String> logNames = logFiles.pull();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("logNames", logNames);
        renderer.render("log-fetch-results", model, response);
    }
}
