package com.vrc.logline.domain;

import com.vrc.logline.controller.Controller;
import com.vrc.logline.controller.HomeController;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.PrintStream;

public class Task implements Runnable {
    private final Request request;
    private final Response response;

    public Task(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void run() {
        try {
            Controller controller = new HomeController();
            controller.act(request,response);
            System.out.println("pr2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

