package org.lee.sql.support;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.Rule;
import org.lee.common.exception.BadConfigurationError;
import org.lee.common.exception.InternalError;
import org.lee.common.utils.RandomUtils;

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
            return RandomUtils.probability(getConfig().getShort(name));
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
        return RandomUtils.probability(prob);
    }

    default boolean probability(short prob){
        return RandomUtils.probability(prob);
    }

    default void setConfig(Rule name, boolean value){
        getConfig().set(name, value);
    }
}
