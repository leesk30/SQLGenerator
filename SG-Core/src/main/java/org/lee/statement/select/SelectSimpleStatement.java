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
    public String getString() {
        return "";
    }

    @Override
    public void fuzz() {

    }

    @Override
    public boolean isScalar() {
        return false;
    }
}
