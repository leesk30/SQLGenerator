package org.lee.portal;

import org.lee.portal.worker.SQLGeneratorWorker;
import org.lee.portal.worker.Worker;

public interface Dispatcher {

    static void forCommandLine(){
        new CommandLineDispatcher().dispatch();
    }

    void dispatch();


}
