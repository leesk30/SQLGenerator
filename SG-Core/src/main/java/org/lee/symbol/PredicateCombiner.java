package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

public enum PredicateCombiner implements Signature {

    NOT(1, "NOT %s"),
    AND(2, "% AND %"),
    OR(2, "%s OR %s"),
    ;

    private final int argNum;
    private final List<TypeTag> argumentsType;
    private final String symbols;
    PredicateCombiner(int argNum, String body){
        this.argNum = argNum;
        this.argumentsType = new ArrayList<>(argNum);
        this.symbols = body;
        IntStream.range(0, argNum).forEach(i -> argumentsType.add(TypeTag.boolean_));
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
    public TypeTag getReturnType() {
        return TypeTag.boolean_;
    }

    @Override
    public List<TypeTag> getArgumentsTypes() {
        return argumentsType;
    }
}
