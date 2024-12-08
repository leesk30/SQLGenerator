package org.lee.portal.worker;

import org.lee.common.config.InternalConfig;
import org.lee.common.worker.SQLGeneratorWorker;
import org.lee.generator.sql.SQLGenerator;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.statement.SQLStatement;

import java.util.concurrent.BlockingQueue;

public class SQLGeneratorDefaultThreadWorker
        extends SQLGeneratorWorker<SQLGenerator, SQLStatement>
        implements Runnable{
    private final BlockingQueue<SQLStatement> result;
    private final InternalConfig config;
    private final int numOfGenerate;
    private SQLGenerator generator;
    private boolean signal = true;
    public SQLGeneratorDefaultThreadWorker(
            int numOfGenerate,
            InternalConfig config,
            BlockingQueue<SQLStatement> result
    ){
        this.result = result;
        this.config = config;
        this.numOfGenerate = numOfGenerate;
    }

    @Override
    public void put(SQLGenerator generator){
        try {
            result.put(generator.generate());
        } catch (Throwable e) {
            e.printStackTrace(); //todo debug
            throw new RuntimeException(e);
        }
    }

    @Override
    public void startup() {
        // do thread local initialize
        generator = new SQLGeneratorContext(config);
    }

    @Override
    public SQLStatement get() {
        try {
            return result.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        startup();
        if(numOfGenerate == -1){
            while (signal){
                put(generator);
            }
            return;
        }
        if (numOfGenerate < 0) {
            throw new IllegalArgumentException("The argument num of generate cannot be lower than -1. Got: " + numOfGenerate);
        }
        for(int i=0; i < numOfGenerate; i++){
            put(generator);
        }
    }
}
