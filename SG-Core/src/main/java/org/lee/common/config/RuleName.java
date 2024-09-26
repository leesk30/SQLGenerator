package org.lee.common.config;

public enum RuleName {
    ENABLE_CTE_RULE,
    ENABLE_FILTER_USING_PROJECTION_ALIAS,
    ENABLE_DUPLICATE_FILED_PROJECTIONS,
    REQUIRE_SCALA,
    AGGREGATION_REQUIRED_GROUP_BY,


    SUPPORT_CTE_MATERIALIZED(false),
    ORDER_DEFAULT_DESC(false),
    ORDER_DEFAULT_NULL_FIRST(false),
    REWRITER_REORDER(false, false, true),
    ;

    private final boolean rewritable;
    private final boolean defaultValue;
    private final boolean diffusible;

    RuleName(){
        this.rewritable = true;
        this.defaultValue = false;
        this.diffusible = false;
    }

    RuleName(boolean rewritable){
        this.rewritable = rewritable;
        this.defaultValue = false;
        this.diffusible = false;
    }

    RuleName(boolean rewritable, boolean defaultValue){
        this.rewritable = rewritable;
        this.defaultValue = defaultValue;
        this.diffusible = false;
    }

    RuleName(boolean rewritable, boolean defaultValue, boolean diffusible){
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
