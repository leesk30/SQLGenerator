package org.lee;


import org.lee.server.SQLGeneratorServer;

import java.io.IOException;


public class SGServerMain {

    private static void serverStart() throws IOException, InterruptedException {
        SQLGeneratorServer SQLGeneratorServer = new SQLGeneratorServer();
        SQLGeneratorServer.start();
        SQLGeneratorServer.blockUntilShutdown();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        serverStart();
    }
}