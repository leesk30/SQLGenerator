package org.lee.common.config;

import org.apache.commons.configuration2.Configuration;
import org.lee.common.SyntaxType;
import org.lee.common.util.FuzzUtil;

import java.util.HashMap;
import java.util.Map;

public class RuntimeConfiguration {
    private final Map<Rule, Boolean> ruleMap = new HashMap<>();
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

    public void set(Rule name, boolean value){
        if(!ruleMap.containsKey(name) || !name.isRewritable()){
            ruleMap.put(name, value);
        }else {
            System.out.println("Cannot overwrite frozen attributes");
        }
    }

    public void set(Conf name, Object value){
        configuration.setProperty(name.toString(), value);
    }

    public boolean getRule(Rule name){
        return ruleMap.getOrDefault(name, provider.getTemplateRuleMap().getOrDefault(name, name.getDefaultValue()));
    }

    public boolean confirm(Rule name){
        return ruleMap.getOrDefault(name, provider.getTemplateRuleMap().getOrDefault(name, name.getDefaultValue()));
    }

    public short getShort(Conf name){
        return configuration.getShort(name.toString(), provider.getDefaultConfig().getShort(name.toString()));
    }

    public int getInt(Conf name){
        return configuration.getInt(name.toString(), provider.getDefaultConfig().getInt(name.toString()));
    }

    public String getString(Conf name){
        return configuration.getString(name.toString(), provider.getDefaultConfig().getString(name.toString()));
    }

    public boolean getBoolean(Conf name){
        return configuration.getBoolean(name.toString(), provider.getDefaultConfig().getBoolean(name.toString()));
    }

    public SyntaxType getSyntaxType(){
        return configuration.getEnum(Conf.SYNTAX_TYPE.toString(), SyntaxType.class);
    }

    public boolean probability(Conf name){
        short probability = getShort(name);
        return FuzzUtil.probability(probability);
    }
}