package org.lee.statement.support;

import org.lee.node.Node;
import org.lee.statement.clause.LimitOffset;
import org.lee.statement.clause.SortByClause;

public interface Sortable extends Node {

    SortByClause getSortByClause();

    /**
     * A sortable statement always with limit and offset
     * */
    LimitOffset getLimitOffset();

}
