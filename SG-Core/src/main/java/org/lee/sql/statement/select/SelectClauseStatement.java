package org.lee.sql.select;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.sql.clause.Clause;
import org.lee.sql.clause.project.SelectClause;
import org.lee.sql.clause.project.SelectClauseWithoutFrom;
import org.lee.sql.support.SQLStatement;

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
