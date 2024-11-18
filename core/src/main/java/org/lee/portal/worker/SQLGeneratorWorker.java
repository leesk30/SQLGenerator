package org.lee.portal.worker;

import org.lee.common.SQLFormatter;

public abstract class SQLGeneratorWorker<INPUT, OUTPUT> implements Worker<INPUT, OUTPUT>{

    protected final SQLFormatter formatter = new SQLFormatter();
    protected boolean terminateFlag = true;

    @Override
    public void terminate() {
        terminateFlag = false;
    }

}
