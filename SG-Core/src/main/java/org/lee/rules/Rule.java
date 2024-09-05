package org.lee.rules;

import org.lee.statement.node.Node;
import org.lee.statement.node.NodeTag;

public interface Rule {
    String N_ENABLE_CTE_RULE = "stmt.cte.enable";
    String N_ENABLE_FILTER_USING_PROJECTION_ALIAS = "stmt.filterUsingAlias.enable";
    boolean pass(Node judgement);
    String getName();
}
