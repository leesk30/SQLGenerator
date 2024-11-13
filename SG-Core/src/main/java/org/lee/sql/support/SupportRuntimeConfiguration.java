package org.lee.sql.support;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.exception.BadConfigurationError;
import org.lee.common.exception.InternalError;

public interface SupportRuntimeConfiguration {
    RuntimeConfiguration getConfig();
    default boolean confirm(Rule name){
        return getConfig().confirm(name);
    }

    default boolean confirm(Conf name){
        return getConfig().getBoolean(name);
    }

    default boolean probability(Conf name){
        try {
            return Utility.probability(getConfig().getShort(name));
        }catch (NumberFormatException e){
            if(!name.isProb()){
                // Like an assertion error
                throw new InternalError(String.format("The conf '%s' is not a probability", name));
            }else {
                throw new BadConfigurationError(name, getConfig().getString(name));
            }
        }
    }

    default boolean probability(int prob){
        return Utility.probability(prob);
    }

    default boolean probability(short prob){
        return Utility.probability(prob);
    }

    default void setConfig(Rule name, boolean value){
        getConfig().set(name, value);
    }
}
