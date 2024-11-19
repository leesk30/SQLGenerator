package org.lee.portal;

import org.lee.common.config.InternalConfigs;

class CommandLineDispatcher implements Dispatcher{
    InternalConfigs.CommandLineOptions options;

    protected CommandLineDispatcher(){
        options = InternalConfigs.readCommandLineOptions();
    }

    @Override
    public void dispatch() {

    }
}
