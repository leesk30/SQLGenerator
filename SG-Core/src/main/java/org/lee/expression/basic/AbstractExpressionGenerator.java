package org.lee.expression.basic;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.expression.Expression;
import org.lee.statement.support.SQLStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractExpressionGenerator<T extends Expression>
        implements IExpressionGenerator<T> {
    protected final SQLStatement statement;
    protected final Logger logger;
    protected final RuntimeConfiguration config;

    protected AbstractExpressionGenerator(SQLStatement statement){
        this.statement = statement;
        if(statement != null){
            this.logger = statement.getLogger();
            this.config = statement.getConfig();
        }else {
            this.logger = LoggerFactory.getLogger("UnknownParent:" + this.getClass().getName());
            this.config = IExpressionGenerator.super.getConfig();
        }
    }

    @Override
    public Logger getLogger(){
        return logger;
    }

    @Override
    public RuntimeConfiguration getConfig(){
        return config;
    }

    @Override
    public SQLStatement retrieveParent() {
        return statement;
    }


}
