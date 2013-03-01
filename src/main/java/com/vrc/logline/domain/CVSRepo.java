package com.vrc.logline.domain;

public class CVSRepo {
    private Config config = Config.get();

    public String getFor(String release, String environment, String path) {
        String relativePath = path.replace(config.userDir() + "\\config\\", "");
        String cvsFilePath = config.userCvsDir() + "/" + release + "/configuration/" + environment + "/" + relativePath;
        System.out.println(cvsFilePath);
        return cvsFilePath;
    }
}
