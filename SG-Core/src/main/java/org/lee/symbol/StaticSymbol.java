package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StaticSymbol implements Signature {
    private final List<TypeTag> arguments;
    private final String symbolBody;
    private final TypeTag returnType;
    public StaticSymbol(String symbolBody, List<TypeTag> arguments, TypeTag returnType){
        this.arguments = arguments;
        this.symbolBody = symbolBody;
        this.returnType = returnType;
        check();
    }

    @Override
    public String getString() {
        return symbolBody;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.operator;
    }

    @Override
    public int argsNum() {
        return arguments.size();
    }

    @Override
    public TypeTag getReturnType() {
        return returnType;
    }

    @Override
    public List<TypeTag> getArgumentsTypes() {
        return arguments;
    }
}
