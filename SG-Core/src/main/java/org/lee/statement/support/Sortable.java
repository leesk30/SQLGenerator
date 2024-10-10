package org.lee.statement.support;

import org.lee.statement.clause.limit.LimitOffset;
import org.lee.statement.clause.sort.SortByClause;

public interface Sortable extends SQLStatement {

    SortByClause getSortByClause();

    /**
     * A sortable statement always with limit and offset
     * */
    LimitOffset getLimitOffset();

}
