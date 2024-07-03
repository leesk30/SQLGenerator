package org.lee.statement;


import org.lee.statement.node.NodeTag;
import org.lee.statement.node.Node;
import org.lee.statement.entry.normalized.NormalizedRelation;
import org.lee.statement.entry.normalized.NormalizedScalar;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.clause.*;

import java.util.List;

public class SelectStatement extends SQLStatement {

    protected SelectStatement parent;

    protected WithClause withClause = new WithClause();
    protected SelectClause selectClause = new SelectClause();
    protected FromClause fromClause = new SelectFromClause();
    protected WhereClause whereClause = new WhereClause();
    protected StartWithClause startWithClause = new StartWithClause();
    protected ConnectByClause connectByClause = new ConnectByClause();
    protected GroupByClause groupByClause = new GroupByClause();
    protected HavingClause havingClause = new HavingClause();
    protected SortByClause sortByClause = new SortByClause();
    protected LimitOffset limitOffset = new SelectLimitOffset();
    protected SelectStatement left = null;
    protected SelectStatement right = null;

    protected SelectStatement(SQLContext context){
        super(context);
        parent = null;
    }

    protected SelectStatement(SelectStatement parent, SQLContext context){
        super(context);
        this.parent = parent;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public List<? extends Node> getChildNodes() {
        return null;
    }

    RangeTableEntry toRelation(){
        return new NormalizedRelation<SelectStatement>(this);
    }

    boolean isScalar(){
        return false;
    }

    Scalar toScalar(){
        if(this.isScalar())
            return new NormalizedScalar<SelectStatement>(this);
        // todo
        return null;
    }

    @Override
    public void generate(SQLStatement statement) {
        if(this.sqlType == SQLType.clause){
            this.selectClause.generate(this);
            return;
        }

        if (this.sqlType == SQLType.setop){
        }
    }
}
