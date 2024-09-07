package org.lee.rules;

import java.util.HashMap;
import java.util.Map;

public class RuleSet {

    private final Map<RuleName, Rule> ruleMap = new HashMap<>();
    private static final Map<String, Map<RuleName, Rule>> defaultRuleMap = new HashMap<>();

    public void put(Rule rule){
        ruleMap.put(rule.getName(), rule);
    }

    public Rule getDefault(RuleName name){
        Map<RuleName, Rule> defaultMap = defaultRuleMap.get(this.getClass().getName());
        return defaultMap == null ? null : defaultMap.get(name);
    }

    public Rule get(RuleName name){
        return ruleMap.get(name);
    }

    public Rule getOrDefault(RuleName name){
        return ruleMap.getOrDefault(name, getDefault(name));
    }

    public boolean confirm(RuleName name){
        final Rule rule = name.isRewritable() ? this.getOrDefault(name) : this.getDefault(name);
        return rule != null && rule.pass();
    }

    protected static void registerRule(String className, Rule rule){
        if(!defaultRuleMap.containsKey(className)){
            defaultRuleMap.put(className, new HashMap<RuleName, Rule>());
        }
        defaultRuleMap.get(className).put(rule.getName(), rule);
    }

    protected static void registerRule(String className, RuleName ruleName, boolean value){
        if(!defaultRuleMap.containsKey(className)){
            defaultRuleMap.put(className, new HashMap<RuleName, Rule>());
        }
        defaultRuleMap.get(className).put(ruleName, new ConstRule(ruleName, value));
    }
}
