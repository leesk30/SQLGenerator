package org.lee.statement;

import org.lee.fuzzer.Fuzzer;
import org.lee.rules.Rule;
import org.lee.rules.RuleSet;
import org.lee.rules.SparkRuleSet;
import org.lee.statement.clause.Clause;
import org.lee.statement.node.Node;
import org.lee.statement.node.TreeNode;
import org.lee.statement.syntax.SQLSyntax;

public abstract class SQLStatement implements TreeNode<Clause<? extends Node>>, Fuzzer {
    protected final SQLGenerateContext context;
    protected final SQLType sqlType;
    protected final SQLStatement parent;
    protected final RuleSet ruleSet;
    protected final SQLSyntax sqlSyntax;

    protected SQLStatement(SQLType sqlType){
        this(sqlType, null);
    }

    protected SQLStatement(SQLType sqlType, SQLStatement parentStatement){
        if(parentStatement == null){
            this.context = new SQLGenerateContext(sqlType);
            this.sqlType = sqlType;
            this.parent = null;
            this.ruleSet = new SparkRuleSet();
        }else {
            this.context = new SQLGenerateContext(parentStatement.getParent().getContext(), sqlType);
            this.sqlType = sqlType;
            this.parent = parentStatement;
            this.ruleSet = parent.ruleSet;
        }
        this.sqlSyntax = SQLSyntax.newSyntax(this);
    }

    public SQLGenerateContext getContext(){
        return context;
    }

    public SQLStatement getParent() {
        return parent;
    }

    public SQLType getSqlType() {
        return sqlType;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void withLimitation(Rule rule){
        ruleSet.put(rule);
    }

    public void withLimitations(Rule ... rules){
        for(Rule rule: rules){
            ruleSet.put(rule);
        }
    }

}
