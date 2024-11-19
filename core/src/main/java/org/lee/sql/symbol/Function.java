package org.lee.sql.symbol;

import org.lee.common.enumeration.NodeTag;
import org.lee.sql.type.TypeTag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Function implements Symbol {

    protected final String body;
    protected final TypeTag returnType;
    protected final List<TypeTag> arguments;
    public Function(String func, TypeTag returnType, TypeTag... argsType){
        this.body = func;
        this.returnType = returnType;
        this.arguments = argsType==null? Collections.emptyList() : Arrays.asList(argsType);
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

    @Override
    public String toString(){
        return String.format(returnType + ":" + body, arguments.toArray());
    }
}
