package org.lee.sql.clause.sort;

import org.lee.sql.statement.select.SelectStatement;

public class SelectOrderByClause extends SortByClause {
    public SelectOrderByClause(SelectStatement statement) {
        super(statement);
    }
}
