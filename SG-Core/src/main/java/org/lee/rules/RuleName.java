package org.lee.rules;

public enum RuleName {
    ENABLE_CTE_RULE,
    ENABLE_FILTER_USING_PROJECTION_ALIAS,
    ENABLE_DUPLICATE_FILED_PROJECTIONS,
    REQUIRE_SCALA,


    SUPPORT_CTE_MATERIALIZED(false),
    ORDER_DEFAULT_DESC(false),
    ORDER_DEFAULT_NULL_FIRST(false),
    ;

    private final boolean rewritable;
    private final boolean isLimitation;

    private RuleName(){
        this.rewritable = false;
        this.isLimitation = false;
    }

    private RuleName(boolean rewritable){
        this.rewritable = rewritable;
        this.isLimitation = false;
    }

    private RuleName(boolean rewritable, boolean isLimitation){
        this.rewritable = rewritable;
        this.isLimitation = isLimitation;
    }

    public boolean isRewritable() {
        return rewritable;
    }

    public boolean isLimitation(){
        return isLimitation;
    }
}
