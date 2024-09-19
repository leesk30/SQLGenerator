package org.lee.statement.select;


import org.lee.rules.DynamicRule;
import org.lee.rules.RuleName;
import org.lee.statement.SQLStatement;
import org.lee.statement.SQLType;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.Projectable;
import org.lee.entry.relation.SubqueryRelation;
import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

import java.util.*;

public abstract class SelectStatement extends SQLStatement implements Projectable {
    protected final SelectType selectType;
    protected final boolean withLogicalParentheses;
    public final int subqueryDepth;
    public final int setopDepth;

    protected final List<TypeTag> limitationsTypes = new Vector<>();

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
                this.withLogicalParentheses = this.selectType != SelectType.simple;
            }
        }else {
            this.subqueryDepth = 1;
            this.setopDepth = 1;
            this.withLogicalParentheses = true;
        }

        ruleSetConstruct();
    }

    @Override
    public List<Clause<? extends Node>> getChildNodes() {
        return new ArrayList<>(childrenMap.values());
    }

    @Override
    public RangeTableEntry toRelation(){
        return new SubqueryRelation(this);
    }

    public SelectType getSelectType() {
        return selectType;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.statement;
    }

    @Override
    public boolean isWithLogicalParentheses() {
        return withLogicalParentheses;
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
        if(selectType == null){
            selectType = FuzzUtil.randomlyChooseFrom(SelectType.ALL);
        }
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
//        return getStatementBySelectType(FuzzUtil.randomlyChooseFrom(SelectType.ALL), parent);
        return getStatementBySelectType(SelectType.normal, parent);
    }

    public abstract List<RangeTableEntry> getRawRTEList();

    private void ruleSetConstruct(){
        ruleSet.put(new DynamicRule(RuleName.ENABLE_CTE_RULE, () -> this.selectType == SelectType.normal));
    }

    public String body(){
        return nodeArrayToString(SPACE, this.walk());
    }

    @Override
    public void withProjectTypeLimitation(List<TypeTag> limitation){
        if(limitationsTypes.isEmpty()){
            limitationsTypes.addAll(limitation);
        }else {
            throw new RuntimeException("Duplicate project limitations");
        }
    }

    @Override
    public List<TypeTag> getProjectTypeLimitation() {
        return limitationsTypes;
    }
}
