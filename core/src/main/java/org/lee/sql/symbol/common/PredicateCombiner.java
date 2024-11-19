package org.lee.sql.symbol.common;

import org.lee.common.enumeration.NodeTag;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeTag;

import java.util.List;

public enum PredicateCombiner implements Symbol {

    NOT(1, "NOT %s"),
    AND(2, "%s AND %s"),
    OR(2, "%s OR %s"),
    ;

    private final int argNum;
    private final List<TypeTag> argumentsType;
    private final String symbols;
    PredicateCombiner(int argNum, String body){
        this.argNum = argNum;
        if(argNum == 1){
            this.argumentsType = TypeTag.ARG_NUM_1;
        }else if(argNum == 2){
            this.argumentsType = TypeTag.ARG_NUM_2;
        }else {
            throw new IllegalArgumentException("The number of Combiner arguments must be 1 or 2");
        }
        this.symbols = body;
        check();
    }

    @Override
    public String getString() {
        return symbols;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.combiner;
    }

    @Override
    public int argsNum() {
        return argNum;
    }

    @Override
    public final TypeTag getReturnType() {
        return TypeTag.boolean_;
    }

    @Override
    public List<TypeTag> getArgumentsTypes() {
        return argumentsType;
    }
}
