package org.lee.statement.clause.project;

import org.lee.common.Utility;
import org.lee.statement.insert.InsertStatement;

public class ValuesClauseForInsert extends ValuesClause{
    protected final int maxLines;
    protected final int minLines = 1;
    public ValuesClauseForInsert(InsertStatement statement) {
        super(statement);
        this.maxLines = 10;
    }

    public ValuesClauseForInsert(InsertStatement statement, int maxLines) {
        super(statement);
        this.maxLines = Math.max(minLines, Math.min(10, maxLines));
    }

    @Override
    public InsertStatement retrieveStatement(){
        return (InsertStatement) statement;
    }

    @Override
    public void fuzz() {
        final int modifyTableMaxWith = retrieveStatement().getModifyTableClause().getChildNodes().get(0).getFields().size();
        length = Utility.randomIntFromRange(minLines, maxLines);
        width = Utility.randomIntFromRange(1, modifyTableMaxWith);
        generateRecord(getTargetTypeByWidth());
    }
}
