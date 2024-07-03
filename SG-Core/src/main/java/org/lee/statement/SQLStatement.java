package org.lee.statement;

import org.lee.fuzzer.FuzzGenerator;
import org.lee.statement.node.TreeNode;
import org.lee.statement.syntax.SQLSyntax;

public abstract class SQLStatement implements TreeNode, FuzzGenerator {
    protected final SQLContext context;
    protected final SQLType sqlType;
    protected final SQLSyntax syntax;
    protected SQLStatement(SQLContext context){
        this.context = context;
        this.sqlType = context.getSqlType();
        this.syntax = SQLSyntax.newSyntaxController(sqlType);
    }
    public SQLContext getContext(){
        return context;
    }
}
