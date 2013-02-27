package com.vrc.logline.controller;

import com.vrc.logline.domain.TimeLine;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.HashMap;
import java.util.Map;

public class LogSearchController extends BaseController implements Controller {

    private static final Logger log = Logger.getLogger(LogSearchController.class);

    public LogSearchController() {
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
        renderer.render("log-search-results", model, response);
    }
}
