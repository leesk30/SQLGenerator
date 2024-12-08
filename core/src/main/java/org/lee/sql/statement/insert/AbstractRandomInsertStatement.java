package org.lee.sql.statement.insert;

import org.lee.context.SQLGeneratorContext;
import org.lee.sql.clause.modify.InsertModifyTableClause;
import org.lee.sql.clause.modify.ModifyTableClause;
import org.lee.sql.clause.project.ValuesClause;
import org.lee.sql.clause.project.ValuesClauseForInsert;

public abstract class AbstractRandomInsertStatement extends InsertStatement {
    protected ModifyTableClause modifyTableClause = new InsertModifyTableClause(this);
    protected ValuesClause valuesClause = new ValuesClauseForInsert(this);

    protected AbstractRandomInsertStatement(SQLGeneratorContext context){
        super(context);
        addClause(modifyTableClause);
        addClause(valuesClause);
    }
}
