package org.lee.statement.select;

import org.lee.statement.SQLStatement;

public final class SelectNormalStatement extends AbstractNormalSelectStatement {

    public SelectNormalStatement() {
        super(SelectType.normal, null);
    }

    public SelectNormalStatement(SQLStatement parent) {
        super(SelectType.normal, parent);
    }

}
