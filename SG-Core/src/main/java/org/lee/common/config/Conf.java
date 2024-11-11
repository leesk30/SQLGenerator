package org.lee.common.config;

public enum Conf {
    MAX_SUBQUERY_RECURSION_DEPTH(3),
    MAX_EXPRESSION_RECURSION_DEPTH(3),
    MAX_CASTING_RECURSION_DEPTH(3),
    MAX_SETOP_RECURSION_DEPTH(3),

    CONVERT_TO_PARTITION_PROB(10, true),
    CONVERT_TO_PIVOTED_PROB(5,true),

    USING_VALUES_IN_FROM_PROBABILITY(1, true),
    USING_SUBQUERY_IN_FROM_PROBABILITY(10, true),

    MAX_FROM_CLAUSE_RTE_JOIN_NUM(3),
    MAX_RTE_JOIN_ENTRY_NUM(3),

    USING_MATERIALIZED_CTE_PROB(5, true),

    MAX_CONDITION_COMBINE_DEPTH(3),
    CONDITION_COMBINE_PROB(15, true),
    CONDITION_COMBINE_GROWTH_PROB(10, true),
    WITH_CTE_PROBABILITY(10, true),
    WHERE_CLAUSE_FUZZ_PROBABILITY(20, true),
    LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY(10, true),
    LIMIT_OFFSET_WITH_OFFSET_PROBABILITY(10, true),
    GROUP_BY_CLAUSE_FUZZ_PROBABILITY(5, true),
    HAVING_CLAUSE_FUZZ_PROBABILITY(5, true),
    SORT_BY_CLAUSE_FUZZ_PROBABILITY(10, true),

    MAX_SELECT_WHERE_FILTER_NUM(5),
    MAX_SELECT_JOIN_FILTER_NUM(3),
    SELECT_JOIN_FILTER_APPEND_PROBABILITY(40, true),
    SELECT_WHERE_FILTER_APPEND_PROBABILITY(40, true),
    EXPRESSION_APPEND_AGGREGATION_PROB(10, true),
    EXPRESSION_RECURSION_PROBABILITY(15, true),
    VALUES_STATEMENT_AS_SUBQUERY_PROBABILITY(2, true),
    SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY(5, true),
    PURE_SELECT_CLAUSE_AS_SUBQUERY_PROBABILITY(1, true),
    EXPRESSION_USING_SCALAR_SUBQUERY_PROBABILITY(3, true),

    MULTI_FIELD_IN_PREDICATE_SUBQUERY_PROBABILITY(5, true),

    ;
    // implement how to check value
    private final boolean isProbability;
    private final String defaultValue;

    <T> Conf(T defaultValue){
        this(defaultValue.toString(), false);
    }

    Conf(String defaultValue){
        this(defaultValue, false);
    }

    <T> Conf(T defaultValue, boolean isProbability){
        this.isProbability = isProbability;
        this.defaultValue = defaultValue.toString();
    }

    Conf(String defaultValue, boolean isProbability){
        this.isProbability = isProbability;
        this.defaultValue = defaultValue;
    }

    public boolean isProb(){
        return isProbability;
    }

    public String getDefaultValue(){
        return defaultValue;
    }

    public static class ParameterValue<T>{

    }
}
