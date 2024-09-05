package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.LimitOffset;
import org.lee.statement.clause.SelectLimitOffset;
import org.lee.statement.clause.SortByClause;
import org.lee.statement.clause.WithClause;
import org.lee.statement.node.Node;

import java.util.Iterator;

public final class SelectNormalStatement extends AbstractNormalSelectStatement {

    public SelectNormalStatement() {
        super(SelectType.normal, null);
    }

    public SelectNormalStatement(SQLStatement parent) {
        super(SelectType.normal, parent);
    }

}
