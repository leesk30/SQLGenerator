package org.lee.common.config;

public enum Rule {
    ENABLE_CTE_RULE,
    ENABLE_FILTER_USING_PROJECTION_ALIAS,
    ENABLE_DUPLICATE_FILED_PROJECTIONS,
    REQUIRE_SCALA,
    PREFER_SCALA_RELATED,
    AGGREGATION_REQUIRED_GROUP_BY,


    SUPPORT_CTE_MATERIALIZED(false, false, true),
    ORDER_DEFAULT_DESC(false, false, true),
    ORDER_DEFAULT_NULL_FIRST(false, false, true),
    REWRITER_REORDER(false, false, true),
    SCALAR_FORCE_USING_AGGREGATION(false, false, true),
    ENABLE_PSEUDO(false, true, true)
    ;

    private final boolean rewritable;
    private final boolean defaultValue;
    private final boolean diffusible;

    Rule(){
        this.rewritable = true;
        this.defaultValue = false;
        this.diffusible = false;
    }

    Rule(boolean rewritable){
        this.rewritable = rewritable;
        this.defaultValue = false;
        this.diffusible = false;
    }

    Rule(boolean rewritable, boolean defaultValue){
        this.rewritable = rewritable;
        this.defaultValue = defaultValue;
        this.diffusible = false;
    }

    Rule(boolean rewritable, boolean defaultValue, boolean diffusible){
        this.rewritable = rewritable;
        this.defaultValue = defaultValue;
        this.diffusible = diffusible;
    }

    public boolean isRewritable() {
        return rewritable;
    }

    public boolean getDefaultValue(){
        return defaultValue;
    }

    public boolean isDiffusible() {
        return diffusible;
    }
}
