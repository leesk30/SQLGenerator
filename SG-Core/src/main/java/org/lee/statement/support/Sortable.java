package org.lee.statement.support;

import org.lee.node.Node;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.limit.LimitOffset;
import org.lee.statement.clause.sort.SortByClause;

public interface Sortable extends Statement<Clause<? extends Node>> {

    SortByClause getSortByClause();

    /**
     * A sortable statement always with limit and offset
     * */
    LimitOffset getLimitOffset();

}
