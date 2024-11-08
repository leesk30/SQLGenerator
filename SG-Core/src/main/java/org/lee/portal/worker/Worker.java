package org.lee.portal.worker;

public interface Worker<IN, OUT>{
    void terminate();
    void startup();

    OUT get();
    void put(IN in);
}
