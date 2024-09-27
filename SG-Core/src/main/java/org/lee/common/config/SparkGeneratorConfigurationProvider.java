package org.lee.common.config;


public class SparkGeneratorConfigurationProvider extends RuntimeConfigurationProvider {

    @Override
    public RuntimeConfiguration newRuntimeConfiguration() {
        return new RuntimeConfiguration(this);
    }

    @Override
    protected void ruleConstructor() {
        this.ruleMapTemplate.put(Rule.ENABLE_CTE_RULE, true);
        this.ruleMapTemplate.put(Rule.ENABLE_FILTER_USING_PROJECTION_ALIAS, false);
        this.ruleMapTemplate.put(Rule.SUPPORT_CTE_MATERIALIZED, false);
        this.ruleMapTemplate.put(Rule.ENABLE_DUPLICATE_FILED_PROJECTIONS, true);
        this.ruleMapTemplate.put(Rule.ORDER_DEFAULT_DESC, false);
        this.ruleMapTemplate.put(Rule.ORDER_DEFAULT_NULL_FIRST, true);
    }
}
