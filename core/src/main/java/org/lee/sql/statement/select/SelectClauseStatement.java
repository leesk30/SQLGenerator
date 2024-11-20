package org.lee.sql.statement.select;

import org.lee.context.SQLGeneratorContext;
import org.lee.sql.clause.Clause;
import org.lee.sql.clause.project.SelectClause;
import org.lee.sql.clause.project.SelectClauseWithoutFrom;
import org.lee.sql.entry.complex.TargetEntry;
import org.lee.sql.entry.relation.RangeTableEntry;

import java.util.Collections;
import java.util.List;

public final class SelectClauseStatement extends SelectStatement{
    private static final List<RangeTableEntry> nonEntryList = Collections.emptyList();

    private final SelectClause targetList = new SelectClauseWithoutFrom(this);

    public SelectClauseStatement(SQLGeneratorContext context) {
        super(SelectType.clause, context);
        addClause(targetList);
    }

    @Override
    public List<TargetEntry> project() {
        return targetList.getChildNodes();
    }

    public Clause<TargetEntry> getSelectClause() {
        return targetList;
    }

    @Override
    public void fuzz() {
        targetList.fuzz();
    }

    @Override
    public boolean isScalar(){
        return targetList.size() == 1;
    }

    @Override
    public List<RangeTableEntry> getRawRTEList() {
        return nonEntryList;
    }

    @Override
    public String body(){
        return targetList.getString();
    }

}
