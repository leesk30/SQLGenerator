package org.lee.portal;

import org.lee.base.Generator;
import org.lee.common.config.InternalConfig;
import org.lee.statement.support.SQLStatement;

import java.util.concurrent.BlockingQueue;

public class SQLGeneratorWorker implements Runnable{
    private final BlockingQueue<SQLStatement> result;
    private final String configFilePath;
    private final int numOfGenerate;
    private boolean signal = true;
    public SQLGeneratorWorker(
            int numOfGenerate,
            String configFilePath,
            BlockingQueue<SQLStatement> result
    ){
        this.result = result;
        this.configFilePath = configFilePath;
        this.numOfGenerate = numOfGenerate;
    }

    private void send(Generator<SQLStatement> generator){
        try {
            result.put(generator.generate());
        } catch (Throwable e) {
            e.printStackTrace(); //todo debug
            throw new RuntimeException(e);
        }
    }

    public void stopIt(){
        signal = false;
    }

    @Override
    public void run() {
        InternalConfig config = InternalConfig.create();
        SQLGeneratorContext context = SQLGeneratorContext.getOrCreate(config);
        Generator<SQLStatement> generator = context.getGenerator();
        if(numOfGenerate == -1){
            while (signal){
                send(generator);
            }
            return;
        }
        if (numOfGenerate < 0) {
            throw new IllegalArgumentException("The argument num of generate cannot be lower than -1. Got: " + numOfGenerate);
        }
        for(int i=0; i < numOfGenerate; i++){
            send(generator);
        }
    }
}
