package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

import java.util.List;

public class Parentheses implements Signature{

    private final Signature wrapped;
    public Parentheses(Signature wrapped){
        this.wrapped = wrapped;
    }

    @Override
    public String getString() {
        return LP + this.wrapped.getString() + RP;
    }

    @Override
    public NodeTag getNodeTag() {
        return this.wrapped.getNodeTag();
    }

    @Override
    public int argsNum() {
        return this.wrapped.argsNum();
    }

    @Override
    public TypeTag getReturnType() {
        return this.wrapped.getReturnType();
    }

    @Override
    public List<TypeTag> getArgumentsTypes() {
        return this.wrapped.getArgumentsTypes();
    }

    @Override
    public Parentheses toParentheses(){
        // stop nest symbol
        return this;
    }
}
