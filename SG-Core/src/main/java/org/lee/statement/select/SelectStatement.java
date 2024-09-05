package org.lee.statement.select;


import org.lee.statement.SQLStatement;
import org.lee.statement.SQLType;
import org.lee.statement.clause.Clause;
import org.lee.statement.common.Projectable;
import org.lee.statement.entry.relation.SubqueryRelation;
import org.lee.statement.node.Node;
import org.lee.statement.node.NodeTag;
import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.util.FuzzUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class SelectStatement extends SQLStatement implements Projectable {
    protected final SelectType selectType;
    protected final boolean withLogicalParentheses;
    protected List<Clause<? extends Node>> children = new ArrayList<>(10);
    public final int subqueryDepth;
    public final int setopDepth;
    public SelectStatement(SelectType selectType){
        this(selectType, null);
    }
    public SelectStatement(SelectType selectType, SQLStatement parent){
        super(SQLType.select, parent);
        this.selectType = selectType;
        if(parent == null){
            this.subqueryDepth = 0;
            this.setopDepth = 0;
            this.withLogicalParentheses = false;
            return;
        }
        if(parent instanceof SelectStatement){
            SelectStatement selectParent = (SelectStatement) parent;
            if(this.selectType == SelectType.setop){
                this.setopDepth = selectParent.setopDepth + 1;
                this.subqueryDepth = selectParent.subqueryDepth;
                this.withLogicalParentheses = false;
            }else {
                this.setopDepth = selectParent.setopDepth;
                this.subqueryDepth = selectParent.selectType == SelectType.setop ? selectParent.subqueryDepth: (selectParent.subqueryDepth + 1);
                this.withLogicalParentheses = true;
            }
        }else {
            this.subqueryDepth = 1;
            this.setopDepth = 1;
            this.withLogicalParentheses = true;
        }

    }

    @Override
    public List<Clause<? extends Node>> getChildNodes() {
        return children;
    }

    @Override
    public RangeTableEntry toRelation(){
        return new SubqueryRelation(this);
    }

    abstract public boolean isScalar();

    public SelectType getSelectType() {
        return selectType;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.statement;
    }

    @Override
    public Iterator<Clause<? extends Node>> walk() {
        return children.iterator();
    }

    public boolean isShell(){
        // the statement just ref another statement in its struct.
        return this.selectType == SelectType.setop;
    }

    public boolean isSubquery(){
        return this.subqueryDepth != 0 || this.setopDepth != 0;
    }

    public static SelectStatement getStatementBySelectType(SelectType selectType){
        return getStatementBySelectType(selectType, null);
    }

    public static SelectStatement getStatementBySelectType(SelectType selectType, SQLStatement parent){
        switch (selectType){
            case normal:
                return new SelectNormalStatement(parent);
            case setop:
                return new SelectSetopStatement(parent);
            case clause:
                return new SelectClauseStatement(parent);
            case simple:
                return new SelectSimpleStatement(parent);
            default:
                throw new RuntimeException("Unexpected token");
        }
    }

    public static SelectStatement randomlyGetStatement(){
        return randomlyGetStatement(null);
    }

    public static SelectStatement randomlyGetStatement(SQLStatement parent){
        return getStatementBySelectType(FuzzUtil.randomlyChooseFrom(SelectType.ALL), parent);
    }

    public abstract List<RangeTableEntry> getRawRTEList();
}
