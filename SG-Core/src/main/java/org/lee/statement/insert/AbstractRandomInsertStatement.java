package org.lee.statement.insert;

import org.lee.statement.clause.modify.InsertModifyTableClause;
import org.lee.statement.clause.modify.ModifyTableClause;
import org.lee.statement.clause.project.ValuesClause;
import org.lee.statement.clause.project.ValuesClauseForInsert;

public abstract class AbstractRandomInsertStatement extends InsertStatement {
    protected ModifyTableClause modifyTableClause = new InsertModifyTableClause(this);
    protected ValuesClause valuesClause = new ValuesClauseForInsert(this);

    protected AbstractRandomInsertStatement(){
        super();
        addClause(modifyTableClause);
        addClause(valuesClause);
    }
}
