package org.lee.portal;

import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.portal.worker.SQLGeneratorDefaultThreadWorker;
import org.lee.statement.support.SQLStatement;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class CommandLineDispatcher implements Dispatcher{
    InternalConfigs.CommandLineOptions options;

    protected CommandLineDispatcher(){
        options = InternalConfigs.readCommandLineOptions();
    }

    @Override
    public void dispatch() {
        int generateNum = options.getGenerateNum();
        InternalConfig config = options.shallowCopy();
        BlockingQueue<SQLStatement> statement = new ArrayBlockingQueue<>(generateNum);
        SQLGeneratorDefaultThreadWorker worker = new SQLGeneratorDefaultThreadWorker(generateNum, config, statement);
    }
}
