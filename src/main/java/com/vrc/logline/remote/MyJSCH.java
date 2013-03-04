package com.vrc.logline.remote;

import com.vrc.logline.domain.Config;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.regex.Pattern;

public class MyJsch implements MyRemote {
    private static final Logger log = Logger.getLogger(MyJsch.class);
    private String machine;
    private Config config;

    @Override
    public MyRemote connect() throws Exception {
        return null;
    }

    @Override
    public List<String> browse(String sourcePath, Pattern pattern) throws Exception {
        return null;
    }

    @Override
    public void download(String sourcePath, String targetPath, Boolean recurse, Pattern pattern) throws Exception {
    }
}
