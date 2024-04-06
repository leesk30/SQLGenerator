package org.lee;


import org.lee.server.SGServer;

import java.io.IOException;


public class SGServerMain {

    private static void serverStart() throws IOException, InterruptedException {
        SGServer sgServer = new SGServer();
        sgServer.start();
        sgServer.blockUntilShutdown();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        serverStart();
    }
}