package org.lee.statement;

import org.lee.common.Parameter;
import org.lee.rules.RuleSet;
import org.lee.statement.literal.Traceable;

import java.util.ArrayList;
import java.util.List;

public class SQLContext{
    protected final SQLType sqlType;

    protected final RuleSet ruleSet;
    protected final Parameter parameter;
    protected final List<Traceable> literalTracer;

    protected SQLContext(SQLType sqlType){
        this.sqlType = sqlType;
        this.ruleSet = new RuleSet();
        this.parameter = new Parameter();
        this.literalTracer = new ArrayList<>();
    }

    protected SQLContext(SQLContext parent, SQLType sqlType){
        this.sqlType = sqlType;
        this.ruleSet = parent.ruleSet;
        this.parameter = parent.parameter;
        this.literalTracer = parent.literalTracer;
    }

    public Parameter getParameter(){
        return parameter;
    }

    public SQLType getSqlType() {
        return sqlType;
    }

    public Integer getNextTraceIndex(){
        synchronized (literalTracer){
            return literalTracer.size() + 1;
        }
    }

}
