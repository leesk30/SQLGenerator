package org.lee.portal.worker;

import org.lee.common.config.InternalConfig;
import org.lee.portal.SQLGenerator;
import org.lee.portal.SQLGeneratorContext;
import org.lee.statement.support.SQLStatement;

import java.util.concurrent.BlockingQueue;

public class SQLGeneratorDefaultThreadWorker
        extends SQLGeneratorWorker<SQLGenerator, SQLStatement>
        implements Runnable{
    public static final int MAX_WORKERS = Runtime.getRuntime().availableProcessors();
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
        generator = SQLGeneratorContext.getOrCreate(config).getGenerator();
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
