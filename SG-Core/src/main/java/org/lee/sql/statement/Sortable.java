package org.lee.sql.support;

import org.lee.sql.clause.limit.LimitOffset;
import org.lee.sql.clause.sort.SortByClause;
import org.lee.sql.statement.SQLStatement;

public interface Sortable extends SQLStatement {

    SortByClause getSortByClause();

    /**
     * A sortable statement always with limit and offset
     * */
    LimitOffset getLimitOffset();

}
