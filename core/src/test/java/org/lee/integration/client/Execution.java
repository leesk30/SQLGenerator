package org.lee.integration.client;

public interface Execution<T>{
    T get();
    boolean isSuccess();
    String getCode();
    void execute();
    Throwable throwable();
}
