package org.lee.common.config;

public final class Version {
    public final static Version instance = new Version();
    public final int major = 0;
    public final int middle = 0;
    public final int minor = 1;
    private Version(){}
    @Override
    public String toString() {
        return String.format("%d.%d.%d", major, middle, minor);
    }
}
