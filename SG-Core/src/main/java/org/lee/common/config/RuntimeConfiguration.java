package org.lee.common.config;

import org.apache.commons.configuration2.Configuration;
import org.lee.common.SyntaxType;

import java.util.HashMap;
import java.util.Map;

public class RuntimeConfiguration {
    private final Map<RuleName, Boolean> ruleMap = new HashMap<>();
    private final Configuration configuration;
    private final RuntimeConfigurationProvider provider;
    public RuntimeConfiguration(RuntimeConfigurationProvider provider){
        this.provider = provider;
        this.configuration = this.provider.getTemplateConfig();
    }

    public RuntimeConfiguration newChildRuntimeConfiguration(){
        RuntimeConfiguration child = this.provider.newRuntimeConfiguration();
        ruleMap.keySet().stream()
                .filter(ruleName -> !ruleName.isDiffusible())
                .forEach(ruleName -> child.set(ruleName, ruleMap.get(ruleName)));
        return child;
    }

    public void set(RuleName name, boolean value){
        if(!ruleMap.containsKey(name) || !name.isRewritable()){
            ruleMap.put(name, value);
        }else {
            System.out.println("Cannot overwrite frozen attributes");
        }
    }

    public void set(ConfigName name, Object value){
        configuration.setProperty(name.toString(), value);
    }

    public boolean getRule(RuleName name){
        return ruleMap.getOrDefault(name, provider.getTemplateRuleMap().getOrDefault(name, name.getDefaultValue()));
    }

    public short getShort(ConfigName name){
        return configuration.getShort(name.toString(), provider.getDefaultConfig().getShort(name.toString()));
    }

    public int getInt(ConfigName name){
        return configuration.getInt(name.toString(), provider.getDefaultConfig().getInt(name.toString()));
    }

    public String getString(ConfigName name){
        return configuration.getString(name.toString(), provider.getDefaultConfig().getString(name.toString()));
    }

    public boolean getBoolean(ConfigName name){
        return configuration.getBoolean(name.toString(), provider.getDefaultConfig().getBoolean(name.toString()));
    }

    public SyntaxType getSyntaxType(){
        return configuration.getEnum(ConfigName.SYNTAX_TYPE.toString(), SyntaxType.class);
    }
}
