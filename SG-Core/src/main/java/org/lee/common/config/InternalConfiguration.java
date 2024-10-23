package org.lee.common.config;

public final class InternalConfiguration {
    public final static InternalConfiguration instance = new InternalConfiguration();

    public final Version version = Version.instance;

    public static InternalConfiguration getInstance(){
        return instance;
    }

    InternalConfiguration(){

    }
}
