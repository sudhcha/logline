package com.vrc.logline.repository;

import com.vrc.logline.controller.AnalysisController;
import com.vrc.logline.controller.Controller;
import com.vrc.logline.controller.HomeController;
import com.vrc.logline.controller.StaticResourceController;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.ArrayList;
import java.util.List;

public class AllControllers {
    private List<Controller> controllers = new ArrayList<Controller>();

    public AllControllers() {
        controllers.add(new StaticResourceController());
        controllers.add(new AnalysisController());
        controllers.add(new HomeController());
    }

    public void act(Request request, Response response) throws Exception {
        for (Controller controller : controllers)
            if (controller.canTake(request)) {
                controller.act(request, response);
                break;
            }
    }
}
