package org.lee.statement.support;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.portal.SQLGeneratorContext;
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
