package com.vrc.logline.domain;

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
            PrintStream body = response.getPrintStream();
            long time = System.currentTimeMillis();

            response.setValue("Content-Type", "text/plain");
            response.setValue("Server", "HelloWorld/1.0 (Simple 4.0)");
            response.setDate("Date", time);
            response.setDate("Last-Modified", time);

            body.println("Hello World");
            body.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

