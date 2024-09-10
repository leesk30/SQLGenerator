package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.*;
import org.lee.entry.complex.Filter;
import org.lee.entry.RangeTableReference;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Scalar;
import org.lee.entry.complex.TargetEntry;
import org.lee.node.NodeTag;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSimpleSelectStatement extends SelectStatement {

    protected SelectClause targetList = new SelectClause(this);
    protected FromClause fromClause = new SelectFromClause(this);
    protected WhereClause whereClause = new SelectWhereClause(this);
    protected StartWithClause startWithClause = new StartWithClause(this);
    protected ConnectByClause connectByClause = new ConnectByClause(this);
    protected GroupByClause groupByClause = new GroupByClause(this);
    protected HavingClause havingClause = new HavingClause(this);

    public AbstractSimpleSelectStatement(SelectType selectType) {
        this(selectType, null);
    }

    public AbstractSimpleSelectStatement(SelectType selectType, SQLStatement parent) {
        super(selectType, parent);
        addClause(targetList);
        addClause(fromClause);
        addClause(whereClause);
        addClause(startWithClause);
        addClause(connectByClause);
        addClause(groupByClause);
        addClause(havingClause);
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
    public List<TargetEntry> project() {
        return targetList.getChildNodes();
    }

    @Override
    public List<RangeTableEntry> getRawRTEList(){
        return fromClause.getRawEntryList();
    }
}
