package org.lee.rules;

public class SparkRuleSet extends RuleSet{
    static {
        registerRule(RuleName.ENABLE_CTE_RULE, true);
        registerRule(RuleName.ENABLE_FILTER_USING_PROJECTION_ALIAS, false);
        registerRule(RuleName.SUPPORT_CTE_MATERIALIZED, false);
        registerRule(RuleName.ENABLE_DUPLICATE_FILED_PROJECTIONS, true);
        registerRule(RuleName.ORDER_DEFAULT_DESC, false);
        registerRule(RuleName.ORDER_DEFAULT_NULL_FIRST, true);
    }

    protected static void registerRule(RuleName ruleName, boolean value){
        RuleSet.registerRule(SparkRuleSet.class.getName(), ruleName, value);
    }
}
