package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

public class Function implements Signature{

    protected final String body;
    protected final TypeTag returnType;
    protected final TypeTag[] arguments;
    public Function(String func, TypeTag returnType, TypeTag... argsType){
        this.body = func;
        this.returnType = returnType;
        this.arguments = argsType;
        check();
    }

    @Override
    public String getString() {
        return body;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.function;
    }

    @Override
    public int argsNum() {
        return arguments.length;
    }

    @Override
    public TypeTag getReturnType() {
        return returnType;
    }
}
