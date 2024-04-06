package org.lee.server;

// as a private class
class ServerProperty {
    private String SG_HOST = "127.0.0.1";
    private int SG_PORT = 9090;

    public ServerProperty(){

    }

    public int getSgPort() {
        return SG_PORT;
    }

    public String getSgHost() {
        return SG_HOST;
    }
}
