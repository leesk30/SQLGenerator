package org.lee.rules;

import org.lee.statement.node.Node;

public class SparkRuleSet extends RuleSet{
    static {
        String className = SparkRuleSet.class.getName();
        SparkRuleSet.registerRuleToDefault(className, new Rule() {
            @Override
            public boolean pass(Node judgement) {
                return false;
            }

            @Override
            public String getName() {
                return Rule.N_ENABLE_FILTER_USING_PROJECTION_ALIAS;
            }
        });

        SparkRuleSet.registerRuleToDefault(className, new Rule() {
            @Override
            public boolean pass(Node judgement) {
                return false;
            }

            @Override
            public String getName() {
                return null;
            }
        });
    }
}
