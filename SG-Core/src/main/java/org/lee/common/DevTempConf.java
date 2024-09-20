package org.lee.common;

public class DevTempConf {
    public final static short MAX_SUBQUERY_RECURSION_DEPTH = 3;
    public final static short MAX_EXPRESSION_RECURSION_DEPTH = 3;
    public final static short MAX_SETOP_RECURSION_DEPTH = 3;

    public final static short CONVERT_TO_PARTITION_PROB = 10;
    public final static short CONVERT_TO_PIVOTED_PROB = 5;
    public final static short USING_VALUES_IN_FROM_PROBABILITY = 1;
    public final static short USING_SUBQUERY_IN_FROM_PROBABILITY = 4;

    public final static short MAX_FROM_CLAUSE_RTE_JOIN_NUM = 3;
    public final static short MAX_RTE_JOIN_ENTRY_NUM = 3;

    public final static short USING_MATERIALIZED_CTE_PROB = 5;

    public final static short MAX_CONDITION_COMBINE_DEPTH = 3;
    public final static short CONDITION_COMBINE_PROB = 15;
    public final static short CONDITION_COMBINE_GROWTH_PROB = 10;
    public final static short WITH_CTE_PROBABILITY = 10;
    public final static short WHERE_CLAUSE_FUZZ_PROBABILITY = 20;
    public final static short LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY = 10;
    public final static short LIMIT_OFFSET_WITH_OFFSET_PROBABILITY = 10;
    public final static short GROUP_BY_CLAUSE_FUZZ_PROBABILITY = 5;
    public final static short HAVING_CLAUSE_FUZZ_PROBABILITY = 5;
    public final static short SORT_BY_CLAUSE_FUZZ_PROBABILITY = 10;

    public final static short MAX_SELECT_WHERE_FILTER_NUM = 5;
    public final static short MAX_SELECT_JOIN_FILTER_NUM = 3;
    public final static short SELECT_JOIN_FILTER_APPEND_PROBABILITY = 40;
    public final static short SELECT_WHERE_FILTER_APPEND_PROBABILITY = 40;

    public final static short EXPRESSION_APPEND_AGGREGATION_PROB = 10;
    public final static short EXPRESSION_RECURSION_PROBABILITY = 10;

    public final static short VALUES_STATEMENT_AS_SUBQUERY_PROBABILITY = 2;
    public final static short SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY = 2;
    public final static short PURE_SELECT_AS_SUBQUERY_PROBABILITY = 1;
}
