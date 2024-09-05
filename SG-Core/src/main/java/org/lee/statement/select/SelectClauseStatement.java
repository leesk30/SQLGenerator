package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.SelectClause;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Field;
import org.lee.statement.entry.scalar.TargetEntry;
import org.lee.symbol.SymbolFinder;

import java.util.ArrayList;
import java.util.Collection;
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
        this.children.add(targetList);
    }

    @Override
    public String getString() {
        return "SELECT " + targetList.getString();
    }

    @Override
    public List<Field> project() {
        final List<Field> projects = new ArrayList<>(targetList.size());
        targetList.getChildNodes().forEach(item -> projects.add(item.toField()));
        return projects;
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
