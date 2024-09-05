package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.*;
import org.lee.statement.complex.Filter;
import org.lee.statement.entry.RangeTableReference;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Field;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.entry.scalar.TargetEntry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSimpleSelectStatement extends SelectStatement {

    protected SelectClause targetList = new SelectClause(this);
    protected FromClause fromClause = new SelectFromClause(this);
    protected WhereClause whereClause = new WhereClause(this);
    protected StartWithClause startWithClause = new StartWithClause(this);
    protected ConnectByClause connectByClause = new ConnectByClause(this);
    protected GroupByClause groupByClause = new GroupByClause(this);
    protected HavingClause havingClause = new HavingClause(this);

    public AbstractSimpleSelectStatement(SelectType selectType) {
        this(selectType, null);
    }

    public AbstractSimpleSelectStatement(SelectType selectType, SQLStatement parent) {
        super(selectType, parent);
    }

    public Clause<Filter> getConnectByClause() {
        return connectByClause;
    }

    public Clause<TargetEntry> getSelectClause() {
        return targetList;
    }

    public Clause<RangeTableReference> getFromClause() {
        return fromClause;
    }

    public Clause<Scalar> getGroupByClause() {
        return groupByClause;
    }

    public Clause<Scalar> getHavingClause() {
        return havingClause;
    }

    public Clause<Filter> getStartWithClause() {
        return startWithClause;
    }

    public Clause<Filter> getWhereClause() {
        return whereClause;
    }

    @Override
    public List<Field> project() {
        final List<Field> projects = new ArrayList<>();
        targetList.getChildNodes().forEach(item -> projects.add(item.toField()));
        return projects;
    }

    @Override
    public List<RangeTableEntry> getRawRTEList(){
        return fromClause.getRawEntryList();
    }
}
