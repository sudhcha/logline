package com.vrc.logline.domain;

import com.vrc.logline.container.AsynchContainer;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class AppServer {

    public void start() throws Exception {
        Container container = new AsynchContainer(10);
        org.simpleframework.transport.Server server = new ContainerServer(container);
        Connection connection = new SocketConnection(server);
        SocketAddress address = new InetSocketAddress(8080);
        connection.connect(address);
    }
}
