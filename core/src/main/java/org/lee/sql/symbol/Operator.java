package org.lee.sql.symbol;

import org.lee.base.NodeTag;
import org.lee.sql.type.TypeTag;

import java.util.Arrays;
import java.util.List;

public final class Operator implements Symbol {

    private final String body;
    private final List<TypeTag> arguments;
    private final TypeTag returnType;
    private final int signaturePriority;
    public Operator(String body, int priority, TypeTag returnType, TypeTag ... arguments){
        this.body = body;
        this.returnType = returnType;
        this.arguments = Arrays.asList(arguments);
        this.signaturePriority = priority;
        check();
    }

    public Operator(String body, int priority, TypeTag returnType, List<TypeTag> arguments){
        this.body = body;
        this.returnType = returnType;
        this.arguments = arguments;
        this.signaturePriority = priority;
        check();
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
    @Override
    public List<TypeTag> getArgumentsTypes() {
        return arguments;
    }

    public int getSignaturePriority() {
        return signaturePriority;
    }
}
