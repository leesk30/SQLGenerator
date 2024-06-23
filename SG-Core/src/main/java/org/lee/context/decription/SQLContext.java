package org.lee.context.decription;

import org.lee.common.Parameter;
import org.lee.context.grammar.Grammar;
import org.lee.node.tree.SQLType;
import org.lee.node.tree.statement.SQLStatement;

public abstract class SQLContext{
    private final Grammar grammar;
    private final Parameter parameter;
    private final SQLType sqlType;
    public SQLContext(SQLType sqlType){
        this.grammar = Grammar
        this.grammar = getGrammar();

    }
    abstract public Grammar getGrammar();
    abstract public Parameter getParameter();
    abstract public Integer getNextTraceIndex();
}
