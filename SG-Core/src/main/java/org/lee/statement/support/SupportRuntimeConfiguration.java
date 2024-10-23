package org.lee.statement.support;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.config.RuntimeConfiguration;

public interface SupportRuntimeConfiguration {
    RuntimeConfiguration getConfig();
    default boolean confirm(Rule name){
        return getConfig().confirm(name);
    }

    default boolean confirm(Conf name){
        return getConfig().getBoolean(name);
    }

    default boolean probability(Conf name){
        if(name.isProb()){
            return Utility.probability(getConfig().getShort(name));
        }
        throw new UnsupportedOperationException(String.format("The conf '%s' is not a probability", name));
    }

    default boolean probability(int prob){
        return Utility.probability(prob);
    }

    default boolean probability(short prob){
        return Utility.probability(prob);
    }

    default void setConfig(Conf name, Object value){
        getConfig().set(name, value);
    }

    default void setConfig(Rule name, boolean value){
        getConfig().set(name, value);
    }
}
