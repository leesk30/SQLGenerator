package org.lee.common.config;


import java.io.File;

public class SparkGeneratorConfigurationProvider extends RuntimeConfigurationProvider {

    @Override
    public RuntimeConfiguration newRuntimeConfiguration() {
        return new RuntimeConfiguration(this);
    }

    @Override
    protected void ruleConstructor() {
        this.ruleMapTemplate.put(RuleName.ENABLE_CTE_RULE, true);
        this.ruleMapTemplate.put(RuleName.ENABLE_FILTER_USING_PROJECTION_ALIAS, false);
        this.ruleMapTemplate.put(RuleName.SUPPORT_CTE_MATERIALIZED, false);
        this.ruleMapTemplate.put(RuleName.ENABLE_DUPLICATE_FILED_PROJECTIONS, true);
        this.ruleMapTemplate.put(RuleName.ORDER_DEFAULT_DESC, false);
        this.ruleMapTemplate.put(RuleName.ORDER_DEFAULT_NULL_FIRST, true);
    }
}
