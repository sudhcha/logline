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
        client.login("vichakra", "bender555fry");

        String filename = "healthcheck.log";
        FileOutputStream fos = new FileOutputStream(filename);
        client.retrieveFile("/was7blue2/logs/healthcheck.log", fos);
        fos.close();
        client.disconnect();
    }

    @Test
    public void shouldConnectViaFTPToListFiles() throws Exception {
        FTPClient client = new FTPClient();
        client.connect("nasnmasdev");
        client.login("vichakra", "bender555fry");

        for (FTPFile ftpFile : client.listFiles("/was7blue2/logs/")) {
            System.out.println(ftpFile.getName());
        }
        client.disconnect();
    }

}
