package com.vrc.logline.controller;

import com.vrc.logline.domain.FileDiff;
import com.vrc.logline.service.FileDiffService;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class FileDiffController extends BaseController {
    private static final Logger log = Logger.getLogger(FileDiffController.class);

    public FileDiffController() {
        super("diff");
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
        renderer.render("log-search-results", model, response);
    }
}
