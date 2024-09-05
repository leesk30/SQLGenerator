package org.lee.statement.node;

import java.util.concurrent.atomic.AtomicInteger;

public interface Alias extends Node{
    boolean hasAlias();
    String getAlias();
    void setAlias();

    AtomicInteger counter = new AtomicInteger(0);
    static String getRandomName(String prefix){
        if(counter.get() % 16384 == 16383){
            counter.set(0);
        }
        int count = counter.addAndGet(1);
        return prefix + "_" + count;
    }

}
