package com.vrc.logline;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.FileOutputStream;

public class ConnectivityTest {

    @Test
    public void shouldConnectViaFTPToFetchFile() throws Exception {
        FTPClient client = new FTPClient();
        client.connect("nasnmasdev");
        client.login("vichakra", "xxx");

        String filename = "healthcheck.log";
        FileOutputStream fos = new FileOutputStream(filename);
        client.retrieveFile("/was7blue2/logs/healthcheck.log", fos);
        fos.close();
        client.disconnect();
    }

    @Test
    public void shouldConnectViaFTPToListAndDownloadSelectedFiles() throws Exception {
        FTPClient client = new FTPClient();
        client.connect("nasnmasdev");
        client.login("vichakra", "xxx");

        for (FTPFile ftpFile : client.listFiles("/was7blue2/logs/")) {
            String name = ftpFile.getName();
            if(!name.contains("router")) continue;
            FileOutputStream fos = new FileOutputStream(name);
            client.retrieveFile("/was7blue2/logs/"+name, fos);
            System.out.println("downloaded "+name);
            fos.close();
        }
        client.disconnect();
    }

}
