package org.lee.common.config;


import org.lee.common.enumeration.Rule;

import java.util.Properties;

public class SparkGeneratorConfigurationProvider extends RuntimeConfigurationProvider {

    public SparkGeneratorConfigurationProvider(){
        super();
    }

    public SparkGeneratorConfigurationProvider(Properties properties) {
        super(properties);
    }

    @Override
    public RuntimeConfiguration newRuntimeConfiguration() {
        return new RuntimeConfiguration(this);
    }

    @Override
    protected void onCreateNewConfigurationProvider() {
        this.ruleMapTemplate.put(Rule.ENABLE_CTE_RULE, true);
        this.ruleMapTemplate.put(Rule.ENABLE_FILTER_USING_PROJECTION_ALIAS, false);
        this.ruleMapTemplate.put(Rule.SUPPORT_CTE_MATERIALIZED, false);
        this.ruleMapTemplate.put(Rule.ENABLE_DUPLICATE_FILED_PROJECTIONS, true);
        this.ruleMapTemplate.put(Rule.ORDER_DEFAULT_DESC, false);
        this.ruleMapTemplate.put(Rule.ORDER_DEFAULT_NULL_FIRST, true);
        // spark required scalar subquery is created by aggregation
//        this.ruleMapTemplate.put(Rule.SCALAR_FORCE_USING_AGGREGATION, true);
        this.ruleMapTemplate.put(Rule.ENABLE_PSEUDO, false);
        this.ruleMapTemplate.put(Rule.ENABLE_SINGLE_RELATION_TRANSFORM_PIVOT, false);
    }
}
