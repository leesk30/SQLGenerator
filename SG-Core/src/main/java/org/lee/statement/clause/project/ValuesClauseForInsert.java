package org.lee.statement.clause.project;

import org.lee.statement.insert.InsertStatement;
import org.lee.common.util.FuzzUtil;

public class ValuesClauseForInsert extends ValuesClause{
    protected final int maxLines;
    protected final int minLines;
    public ValuesClauseForInsert(InsertStatement statement) {
        super(statement);
        this.maxLines = 10;
        this.minLines = 1;
    }

    public ValuesClauseForInsert(InsertStatement statement, int maxLines) {
        super(statement);
        this.minLines = 1;
        this.maxLines = Math.max(minLines, Math.min(10, maxLines));
    }

    @Override
    public InsertStatement statement(){
        return (InsertStatement) statement;
    }

    @Override
    public void fuzz() {
        final int modifyTableMaxWith = statement().getModifyTableClause().getChildNodes().get(0).getFields().size();
        length = FuzzUtil.randomIntFromRange(minLines, maxLines);
        width = FuzzUtil.randomIntFromRange(1, modifyTableMaxWith);
        generateRecord(getTargetTypeByWidth());
    }
}
