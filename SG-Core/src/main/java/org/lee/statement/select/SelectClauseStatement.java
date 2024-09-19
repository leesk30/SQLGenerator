package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.project.SelectClause;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.complex.TargetEntry;
import org.lee.statement.clause.project.SelectClauseWithoutFrom;

import java.util.Collections;
import java.util.List;

public final class SelectClauseStatement extends SelectStatement{
    private static final List<RangeTableEntry> nonEntryList = Collections.emptyList();

    private final SelectClause targetList = new SelectClauseWithoutFrom(this);

    public SelectClauseStatement() {
        this(null);
    }

    public SelectClauseStatement(SQLStatement parent) {
        super(SelectType.clause, parent);
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
