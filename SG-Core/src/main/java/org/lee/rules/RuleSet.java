package org.lee.rules;

import org.lee.common.Parameter;
import org.lee.statement.node.Node;

import java.util.HashMap;
import java.util.Map;

public class RuleSet {

    private final Map<String, Rule> ruleMap = new HashMap<>();
    private static final Map<String, Map<String, Rule>> defaultRuleMap = new HashMap<>();

    public void put(Rule rule){
        ruleMap.put(rule.getName(), rule);
    }

    public Rule getDefault(String name){
        Map<String, Rule> defaultMap = defaultRuleMap.get(this.getClass().getName());
        return defaultMap == null ? null : defaultMap.get(name);
    }

    public Rule get(String name){
        return ruleMap.get(name);
    }

    public Rule getOrDefault(String name){
        return ruleMap.getOrDefault(name, getDefault(name));
    }

    public boolean isPass(String name, Node judgement){
        Rule rule = this.get(name);
        return rule != null && rule.pass(judgement);
    }

    protected static void registerRuleToDefault(String className, Rule rule){
        if(!defaultRuleMap.containsKey(className)){
            defaultRuleMap.put(className, new HashMap<String, Rule>());
        }
        defaultRuleMap.get(className).put(rule.getName(), rule);
    }
}
