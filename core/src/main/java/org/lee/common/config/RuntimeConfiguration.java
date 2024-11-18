package org.lee.common.config;

import org.lee.common.NamedLoggers;
import org.lee.common.Utility;
import org.lee.common.global.SymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;

public class RuntimeConfiguration {
    private final Map<Rule, Boolean> ruleMap = new EnumMap<>(Rule.class);
    private final Map<Conf, String> confMap = new EnumMap<>(Conf.class);
    private final RuntimeConfigurationProvider provider;
    private final static Logger LOGGER = NamedLoggers.getCoreLogger(RuntimeConfiguration.class);

    public RuntimeConfiguration(RuntimeConfigurationProvider provider){
        this.provider = provider;
        Properties properties = this.provider.getTemplateConfig();
        for(Conf conf: Conf.values()){
            if(properties.containsKey(conf)){
                confMap.put(conf, properties.getProperty(conf.toString()));
            }
        }
    }

    public RuntimeConfiguration newChildRuntimeConfiguration(){
        RuntimeConfiguration child = this.provider.newRuntimeConfiguration();
        ruleMap.keySet().stream()
                .filter(ruleName -> {
                    if(ruleName.isDiffusible()){
                        LOGGER.debug(String.format("The rule configuration named '%s' diffuse to child runtime configuration. The value is: %s", ruleName, ruleMap.get(ruleName)));
                        return true;
                    }
                    return false;
                })
                .forEach(ruleName -> child.set(ruleName, ruleMap.get(ruleName)));
        return child;
    }

    public void set(Rule name, boolean value){
        if(!ruleMap.containsKey(name) || !name.isRewritable()){
            ruleMap.put(name, value);
        }else {
            String errorMessage = String.format("Cannot overwrite frozen attributes. origin %s = %s", name, ruleMap.get(name));
            LOGGER.error(errorMessage);
            throw new UnsupportedOperationException(errorMessage);
        }
    }

    public boolean confirm(Rule name){
        return ruleMap.getOrDefault(name, provider.getTemplateRuleMap().getOrDefault(name, name.getDefaultValue()));
    }

    public short getShort(Conf name){
        return Short.parseShort(getString(name));
    }

    public int getInt(Conf name){
        return Integer.parseInt(getString(name));
    }

    public String getString(Conf name){
        return confMap.getOrDefault(name, name.getDefaultValue());
    }

    public boolean getBoolean(Conf name){
        return Boolean.parseBoolean(getString(name));
    }

    public boolean probability(Conf name){
        short probability = getShort(name);
        return Utility.probability(probability);
    }

    public boolean contains(Conf name){
        return confMap.containsKey(name);
    }

    public boolean contains(Rule name){
        return ruleMap.containsKey(name);
    }
}
