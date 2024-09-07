package org.lee.statement;

import org.lee.common.Parameter;
import org.lee.rules.RuleSet;
import org.lee.entry.literal.Traceable;

import java.util.ArrayList;
import java.util.List;

public class SQLGenerateContext {
    private final SQLType sqlType;
    private final RuleSet ruleSet;
    private final Parameter parameter;
    private final List<Traceable> literalTracer;

    protected SQLGenerateContext(SQLType sqlType){
        this.sqlType = sqlType;
        this.parameter = new Parameter();
        this.ruleSet = parameter.toRuleSet();
        this.literalTracer = new ArrayList<>();
    }

    protected SQLGenerateContext(SQLGenerateContext parent, SQLType sqlType){
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

    public RuleSet getRuleSet() {
        return ruleSet;
    }
}
