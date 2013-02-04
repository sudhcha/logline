package com.vrc.logline.container;

import com.vrc.logline.domain.Task;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsynchContainer implements Container {

    private final Executor executor;

    public AsynchContainer(int size) {
        this.executor = Executors.newFixedThreadPool(size);
    }

    @Override
    public void handle(Request request, Response response) {
        Task task = new Task(request, response);
        this.executor.execute(task);
    }
}
