package org.lee.sql.support;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.statement.SQLStatement;
import org.slf4j.Logger;

public interface SQLStatementChildren extends SupportRuntimeConfiguration, Logging{
    SQLStatement retrieveParent();

    @Override
    default RuntimeConfiguration getConfig(){
        SQLStatement statement = retrieveParent();
        if(statement != null){
            return retrieveParent().getConfig();
        }
        return SQLGeneratorContext.getCurrentConfigProvider().newRuntimeConfiguration();
    }

    @Override
    default Logger getLogger() {
        SQLStatement statement = retrieveParent();
        if(statement != null){
            return retrieveParent().getLogger();
        }
        return SQLGeneratorContext.getCurrentLogger();
    }
}