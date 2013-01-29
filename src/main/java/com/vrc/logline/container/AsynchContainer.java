package com.vrc.logline.container;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import java.io.PrintStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsynchContainer implements Container {

    private static class Task implements Runnable{
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
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private final Executor executor;

    public AsynchContainer(int size) {
        this.executor = Executors.newFixedThreadPool(size);
    }

    @Override
    public void handle(Request request, Response response) {
       Task task = new Task(request,response);
        this.executor.execute(task);
    }
}
