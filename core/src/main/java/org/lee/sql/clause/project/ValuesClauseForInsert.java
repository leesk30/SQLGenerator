package org.lee.sql.clause.project;

import org.lee.common.utils.RandomUtils;
import org.lee.sql.statement.insert.InsertStatement;

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
    public InsertStatement retrieveParent(){
        return (InsertStatement) statement;
    }

    @Override
    public void fuzz() {
        final int modifyTableMaxWith = this.retrieveParent().getModifyTableClause().getChildNodes().get(0).getFields().size();
        length = RandomUtils.randomIntFromRange(minLines, maxLines);
        width = RandomUtils.randomIntFromRange(1, modifyTableMaxWith);
        generateRecord(getTargetTypeByWidth());
    }
}
