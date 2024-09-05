package org.lee.statement.syntax;

import org.lee.statement.SQLType;
import org.lee.statement.select.SelectStatement;

public class SelectSyntax extends SQLSyntax {
    private boolean allowAgg;
    private boolean allowSetOp;
    private boolean allow;
    public SelectSyntax(SelectStatement statement){
        super(statement);
        this.isModifyTable = false;
    }

    @Override
    public void fuzz() {
    }
}
