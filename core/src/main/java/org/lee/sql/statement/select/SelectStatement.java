package org.lee.sql.statement.select;


import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.sql.SQLGeneratorContext;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.relation.RangeTableEntry;
import org.lee.sql.entry.relation.SubqueryRelation;
import org.lee.sql.statement.AbstractSQLStatement;
import org.lee.sql.statement.Projectable;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.common.SQLType;
import org.lee.sql.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public abstract class SelectStatement extends AbstractSQLStatement implements Projectable {
    protected final SelectType selectType;
    protected final boolean withLogicalParentheses;
    public final int subqueryDepth;
    public final int setopDepth;

    protected final List<TypeTag> limitationsTypes = new ArrayList<>();

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
            // Increment each kind of recursion depth
            SelectStatement selectParent = (SelectStatement) parent;
            if(this.selectType == SelectType.setop){
                this.setopDepth = selectParent.setopDepth + 1;
                this.subqueryDepth = selectParent.subqueryDepth;
                this.withLogicalParentheses = true;
            }else {
                this.setopDepth = selectParent.setopDepth;
                this.subqueryDepth = selectParent.selectType == SelectType.setop ? selectParent.subqueryDepth: (selectParent.subqueryDepth + 1);
                this.withLogicalParentheses = (selectParent.selectType != SelectType.setop || this.selectType != SelectType.simple);
            }
        }else {
            // The ValuesStatement cannot be the parent of a select statement.
            //  That means parent here are all DML.
            //  So we should give an initial depth directly.
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
        // todo
        if(selectType == null){
            if(parent != null){
                if(parent.enableSetop() && parent.probability(Conf.SETOP_STATEMENT_AS_SUBQUERY_PROBABILITY)){
                    selectType = SelectType.setop;
                }else {
                    selectType = Utility.randomlyChooseFrom(SelectType.ALL);
                }
            } else {
                RuntimeConfiguration configuration = SQLGeneratorContext.getCurrentConfigProvider().newRuntimeConfiguration();
                if(configuration.confirm(Rule.REWRITER_REORDER)){
                    selectType = Utility.probability(5) ? SelectType.setop : SelectType.normal;
                }else {
                    selectType = Utility.randomlyChooseFrom(SelectType.ALL);
                }
            }
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

    @Override
    public boolean enableSetop() {
        return this.subqueryDepth < config.getShort(Conf.MAX_SUBQUERY_RECURSION_DEPTH);
    }

    @Override
    public boolean enableSubquery() {
        return this.setopDepth < config.getShort(Conf.MAX_SETOP_RECURSION_DEPTH);
    }
}
