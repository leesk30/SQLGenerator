package org.lee.sql.symbol;

import org.lee.base.NodeTag;
import org.lee.sql.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Parentheses implements Symbol {
    private static final Logger LOGGER = LoggerFactory.getLogger(Parentheses.class);

    private final Symbol wrapped;
    public Parentheses(Symbol wrapped){
        if(wrapped instanceof Parentheses){
            LOGGER.error("There is no sense for the parentheses nest directly!");
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