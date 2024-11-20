package org.lee;


import org.lee.server.ServerBootstrap;

import java.io.IOException;


public class SQLGeneratorServerMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerBootstrap ServerBootstrap = new ServerBootstrap();
        ServerBootstrap.start();
        ServerBootstrap.blockUntilShutdown();
    }
}