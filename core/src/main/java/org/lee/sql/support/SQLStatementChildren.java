package org.lee.sql.support;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.statement.SQLStatement;

public interface SQLStatementChildren extends SupportRuntimeConfiguration{
    SQLStatement retrieveParent();

    SQLGeneratorContext retrieveContext();

    @Override
    default RuntimeConfiguration getConfig(){
        SQLStatement statement = retrieveParent();
        if(statement != null){
            return retrieveParent().getConfig();
        }
        return retrieveContext().newRuntimeConfiguration();
    }

}
