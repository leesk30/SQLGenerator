package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.Arrays;
import java.util.List;

public class Operator implements Signature{

    private final String body;
    private final List<TypeTag> arguments;
    private final TypeTag returnType;
    public Operator(String body, TypeTag returnType, TypeTag ... arguments){
        this.body = body;
        this.returnType = returnType;
        this.arguments = Arrays.asList(arguments);
    }

    public Operator(String body, TypeTag returnType, List<TypeTag> arguments){
        this.body = body;
        this.returnType = returnType;
        this.arguments = arguments;
    }

    @Override
    public String getString() {
        return this.body;
    }

    @Override
    public TypeTag getReturnType() {
        return returnType;
    }

    @Override
    public int argsNum() {
        return arguments.size();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.operator;
    }

    public static void callEmpty(){
        // do nothing but init
    }

    @Override
    public List<TypeTag> getArgumentsTypes() {
        return arguments;
    }
}
