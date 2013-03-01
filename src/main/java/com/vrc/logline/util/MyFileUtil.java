package com.vrc.logline.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtil {

    public static List<String> getFilePaths(String path) {
        List<String> paths = new ArrayList<String>();
        recurseFile(new File(path), paths);
        return paths;
    }

    private static void recurseFile(File target, List<String> paths) {
        for (File file : target.listFiles()) {
            if (file.isDirectory()) {
                recurseFile(file, paths);
                continue;
            }
            paths.add(file.getAbsolutePath());
        }
    }

    public static void recreate(String path) throws Exception {
        File dir = new File(path);
        if (dir.exists())
            FileUtils.deleteDirectory(dir);
        dir.mkdir();
    }
}
