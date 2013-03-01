package com.vrc.logline.domain;

import org.apache.log4j.Logger;

public class CVSRepo {
    private static final Logger log = Logger.getLogger(CVSRepo.class);
    private Config config = Config.get();

    public String getFor(String release, String environment, String path) {
        String relativePath = path.replace(config.userDir() + "\\config\\", "");
        String cvsFilePath = config.userCvsDir() + "/" + release + "/configuration/" + environment + "/" + relativePath;
        return cvsFilePath;
    }
}
