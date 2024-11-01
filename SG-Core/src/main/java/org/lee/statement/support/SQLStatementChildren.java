package org.lee.statement.support;

import org.lee.portal.SQLGeneratorContext;
import org.lee.common.config.RuntimeConfiguration;
import org.slf4j.Logger;

public interface SQLStatementChildren extends SupportRuntimeConfiguration, Logging{
    SQLStatement retrieveStatement();

    @Override
    default RuntimeConfiguration getConfig(){
        SQLStatement statement = retrieveStatement();
        if(statement != null){
            return retrieveStatement().getConfig();
        }
        return SQLGeneratorContext.getCurrentConfigProvider().newRuntimeConfiguration();
    }

    @Override
    default Logger getLogger() {
        SQLStatement statement = retrieveStatement();
        if(statement != null){
            return retrieveStatement().getLogger();
        }
        return SQLGeneratorContext.getCurrentLogger();
    }
}
