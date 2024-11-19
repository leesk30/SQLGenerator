package org.lee.common.worker;

public abstract class SQLGeneratorWorker<INPUT, OUTPUT> implements Worker<INPUT, OUTPUT>{

    protected boolean terminateFlag = true;

    @Override
    public void terminate() {
        terminateFlag = false;
    }

}
