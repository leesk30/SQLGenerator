package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.SelectClause;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Field;
import org.lee.entry.complex.TargetEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SelectClauseStatement extends SelectStatement{
    private static final List<RangeTableEntry> nonEntryList = Collections.emptyList();

    private final SelectClause targetList = new SelectClause(this);

    public SelectClauseStatement() {
        this(null);
    }

    public SelectClauseStatement(SQLStatement parent) {
        super(SelectType.clause, parent);
        this.childrenMap.put(targetList.getNodeTag(), targetList);
    }

    @Override
    public String getString() {
        return targetList.getString();
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
        // todo
    }

    @Override
    public boolean isScalar(){
        return targetList.size() == 1;
    }

    @Override
    public List<RangeTableEntry> getRawRTEList() {
        return nonEntryList;
    }
}
