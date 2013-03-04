package com.vrc.logline.controller;

import com.vrc.logline.service.LogFetchService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogDownloadController extends BaseController {
    private static final Logger log = Logger.getLogger(LogDownloadController.class);

    public LogDownloadController() {
        super("log-download");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        log.info(request.getPath());
        addHeaders(response);
        String machine = request.getParameter("machine");
        List<String> logFileNames = split(request.getParameter("logFileNames"));
        List<String> logNames = new LogFetchService().getFiles(machine, logFileNames);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("logNames", logNames);
        renderer.render("log-download-results", model, response);
    }

    private List<String> split(String parameter) {
        return Arrays.asList(StringUtils.split(parameter, ","));
    }
}
