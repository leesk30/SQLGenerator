package org.lee.common.config;

import org.lee.common.exception.InternalError;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// todo add more config in here
public class ServerConfig {
    public static final ServerConfig PROPERTY = new ServerConfig();

    private final String HOST = "127.0.0.1";
    private final int PORT = 9090;

    private ServerConfig(){
        InputStream stream = ServerConfig.class.getClassLoader().getResourceAsStream("");
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new InternalError(e);
        }
    }

    public int getListenPort() {
        return PORT;
    }

    public String getListenHost() {
        return HOST;
    }
}
