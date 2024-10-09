package org.lee.symbol;

import org.lee.base.NodeTag;
import org.lee.type.TypeTag;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Parentheses implements Signature{

    private final Signature wrapped;
    public Parentheses(Signature wrapped){
        if(wrapped instanceof Parentheses){
            LoggerFactory.getLogger(this.getClass()).error("There is no sense for the parentheses nest directly!");
            throw new UnsupportedOperationException("There is no sense for the parentheses nest directly!");
        }
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
