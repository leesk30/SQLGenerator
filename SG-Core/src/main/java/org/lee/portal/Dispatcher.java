package org.lee.portal;

public interface Dispatcher {

    static void forCommandLine(){
        new CommandLineDispatcher().dispatch();
    }

    void dispatch();


}
