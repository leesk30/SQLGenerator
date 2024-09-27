package org.lee.statement.support;

import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.common.util.FuzzUtil;

public interface SupportRuntimeConfiguration {
    RuntimeConfiguration getConfig();
    default boolean confirm(Rule name){
        return getConfig().confirm(name);
    }

    default boolean confirm(Conf name){
        return getConfig().getBoolean(name);
    }

    default boolean probability(Conf name){
        return FuzzUtil.probability(getConfig().getShort(name));
    }

    default boolean probability(int prob){
        return FuzzUtil.probability(prob);
    }

    default boolean probability(short prob){
        return FuzzUtil.probability(prob);
    }
}
