package org.lee.statement.select;


import org.lee.statement.SQLStatement;

public final class SelectSimpleStatement extends AbstractSimpleSelectStatement {
    public SelectSimpleStatement() {
        super(SelectType.simple);
    }

    public SelectSimpleStatement(SQLStatement parent) {
        super(SelectType.simple, parent);
    }

    @Override
    public void fuzz() {
        fromClause.fuzz();
        whereClause.fuzz();
        targetList.fuzz();
        groupByClause.fuzz();
        havingClause.fuzz();
    }

    @Override
    public boolean isScalar() {
        return false;
    }
}
