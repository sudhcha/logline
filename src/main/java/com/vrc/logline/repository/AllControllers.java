package com.vrc.logline.repository;

import com.vrc.logline.controller.*;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.util.ArrayList;
import java.util.List;

public class AllControllers {
    private List<Controller> controllers = new ArrayList<Controller>();

    public AllControllers() {
        controllers.add(new StaticController());
        controllers.add(new LogSearchController());
        controllers.add(new LogFetchController());
        controllers.add(new SettingsController());
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
