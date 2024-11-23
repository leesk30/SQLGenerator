package org.lee.common.config;

import org.lee.common.exception.InternalError;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

// todo add more config in here
public class ServerConfig {
    public static final ServerConfig PROPERTY = new ServerConfig();

    private final String host;
    private final int port;
    private final int cacheSize;
    private final int expireTime;
    private final TimeUnit timeunit;

    private ServerConfig(){
        InputStream stream = ServerConfig.class.getClassLoader().getResourceAsStream("");
        Properties properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            throw new InternalError(e);
        }
        host = properties.getProperty("server.host");
        port = Integer.parseInt(properties.getProperty("server.port"));
        cacheSize = Integer.parseInt(properties.getProperty("server.context.cache.size"));
        expireTime = Integer.parseInt(properties.getProperty("server.context.cache.expire"));
        timeunit = toTimeUnit(properties.getProperty("server.context.cache.timeunit"));

    }

    private TimeUnit toTimeUnit(String s){
        switch (s.trim().toLowerCase()){
            case "m":
            case "minute":
            case "minutes":
                return TimeUnit.MINUTES;
            case "d":
            case "day":
            case "days":
                return TimeUnit.DAYS;
            case "h":
            case "hour":
            case "hours":
                return TimeUnit.HOURS;
            case "ms":
            case "millisecond":
            case "milliseconds":
                return TimeUnit.MILLISECONDS;
            case "s":
            case "second":
            case "seconds":
                return TimeUnit.SECONDS;
            case "mm":
            case "mcs":
            case "microsecond":
            case "microseconds":
                return TimeUnit.MICROSECONDS;
            case "ns":
            case "nano":
            case "nanosecond":
            case "nanoseconds":
                return TimeUnit.NANOSECONDS;
        }
        throw new IllegalArgumentException("The argument is illegal timeunit: " + s);
    }

    public int getListenPort() {
        return port;
    }

    public String getListenHost() {
        return host;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public TimeUnit getTimeunit() {
        return timeunit;
    }

    public int getCacheBucketSize() {
        return Math.max(cacheSize / 4, 10);
    }

}
