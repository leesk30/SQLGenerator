package org.lee.portal;

import org.lee.common.config.InternalConfig;
import org.lee.common.config.InternalConfigs;
import org.lee.portal.worker.SQLGeneratorWorker;
import org.lee.statement.support.SQLStatement;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CommandLineDispatcher implements Dispatcher{


    protected CommandLineDispatcher(){
        InternalConfigs.CommandLineOptions options = InternalConfigs.readCommandLineOptions();

    }

    @Override
    public void dispatch() {
        InternalConfigs.CommandLineOptions options = InternalConfigs.readCommandLineOptions();
        int generateNum = options.getGenerateNum();
        InternalConfig config = options.shallowCopy();
        BlockingQueue<SQLStatement> statement = new ArrayBlockingQueue<>(generateNum);
        SQLGeneratorWorker worker = new SQLGeneratorWorker(generateNum, config, statement);
    }
}
