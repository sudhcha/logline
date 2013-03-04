package com.vrc.logline;

import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ConnectivityTest {

    @Test
    public void shouldConnectViaFTPToFetchFile() throws Exception {
        InetAddress address = InetAddress.getByName("nasnmasmo");
        System.out.println(address.getHostAddress());
//        System.out.println(address.isReachable(4000));
        SocketAddress sockaddr = new InetSocketAddress(address, 22);
        Socket socket1 = new Socket();
        socket1.connect(sockaddr, 22);
        System.out.println(socket1.isConnected());
    }

    @Test
    public void shouldConnectViaFTPToListAndDownloadSelectedFiles() throws Exception {
        FTPClient client = new FTPClient();
        client.connect("nasnmasdev");
        client.login("vichakra", "####");

        for (FTPFile ftpFile : client.listFiles("/was7blue2/logs/")) {
            String name = ftpFile.getName();
            if (!name.contains("router")) continue;
            FileOutputStream fos = new FileOutputStream(name);
            client.retrieveFile("/was7blue2/logs/" + name, fos);
            System.out.println("downloaded " + name);
            fos.close();
        }
        client.disconnect();
    }

    @Test
    public void shouldUseJSch() {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("vichakra", "nasnmasmo", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("####");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get("public/csrs-timesheets", "csr.txt");
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }

    }

}
