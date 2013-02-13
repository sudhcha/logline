package com.vrc.logline.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class StaticController extends BaseController {
    private static final Logger log = Logger.getLogger(HomeController.class);

    public StaticController() {
        super("static");
    }

    @Override
    public void act(Request request, Response response) throws Exception {
        String directory = ClassLoader.getSystemResource("static").getPath();
        String fileName = StringUtils.substringAfter(request.getPath().toString(), "static");
        String fileContent = FileUtils.readFileToString(new File(directory + fileName));

        Writer writer = new OutputStreamWriter(response.getPrintStream());
        writer.write(fileContent);
        writer.close();
        log.info(request.getPath());
    }
}
