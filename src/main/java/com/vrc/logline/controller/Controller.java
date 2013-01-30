package com.vrc.logline.controller;

import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

public interface Controller {

    void act(Request request, Response response) throws Exception;
}
