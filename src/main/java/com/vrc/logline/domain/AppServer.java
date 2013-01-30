package com.vrc.logline.domain;

import com.vrc.logline.container.AsynchContainer;
import org.simpleframework.http.core.Container;
import org.simpleframework.http.core.ContainerServer;
import org.simpleframework.transport.Server;
import org.simpleframework.transport.connect.Connection;
import org.simpleframework.transport.connect.SocketConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class AppServer {

    private Server server;

    public void start() throws Exception {
        Container container = new AsynchContainer(10);
        server = new ContainerServer(container);
        Connection connection = new SocketConnection(server);
        SocketAddress address = new InetSocketAddress(8080);
        connection.connect(address);
    }

    public void stop() throws Exception {
        server.stop();
    }
}
