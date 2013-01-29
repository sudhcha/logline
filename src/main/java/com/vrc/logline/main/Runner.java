package com.vrc.logline.main;

import com.vrc.logline.container.AsynchContainer;
import com.vrc.logline.container.SynchContainer;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Runner {

    public static void main(String[] list) throws Exception {
        Container container = new AsynchContainer(10);
        Server server = new ContainerServer(container);
        Connection connection = new SocketConnection(server);
        SocketAddress address = new InetSocketAddress(8080);
        connection.connect(address);
    }
}
