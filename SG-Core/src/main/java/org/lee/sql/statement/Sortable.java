package org.lee.sql.statement;

import org.lee.sql.clause.limit.LimitOffset;
import org.lee.sql.clause.sort.SortByClause;

public interface Sortable extends SQLStatement {

    SortByClause getSortByClause();

    /**
     * A sortable statement always with limit and offset
     * */
    LimitOffset getLimitOffset();

}
