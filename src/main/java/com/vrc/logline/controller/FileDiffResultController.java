package com.vrc.logline.controller;

import com.vrc.logline.service.FileDiffService;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class FileDiffResultController extends BaseController {
    private static final Logger log = Logger.getLogger(FileDiffResultController.class);

    public FileDiffResultController() {
        super("config-result");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        log.info(request.getPath());
        addHeaders(response);
        String machineName = request.getParameter("machine");
        String releaseName = request.getParameter("release");

        Map<String, Object> model = new HashMap<String, Object>();
        FileDiffService fileDiffService = new FileDiffService();
        model.put("fileDiffs", fileDiffService.process(machineName, releaseName));
        renderer.render("diff-results", model, response);
    }
}
