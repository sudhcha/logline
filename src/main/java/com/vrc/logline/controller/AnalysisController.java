package com.vrc.logline.controller;

import com.vrc.logline.domain.TimeLine;
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
        log.info(request.getPath());
        addHeaders(response);

        String keys = request.getParameter("keys");
        String folder = request.getParameter("folder");
        TimeLine timeLine = new TimeLine(keys, folder);
        timeLine.process();

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("keyLines", timeLine.keyLines());
        model.put("errorLines", timeLine.errorLines());
        renderer.render("results", model, response);
    }
}
