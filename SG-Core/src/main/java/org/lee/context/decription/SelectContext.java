package org.lee.context.decription;

import org.lee.common.Parameter;
import org.lee.context.grammar.Grammar;

public abstract class SelectContext extends SQLContext {
    @Override
    public Grammar getGrammar() {
        return null;
    }

    @Override
    public Parameter getParameter() {
        return null;
    }


    abstract public int getRecursionLevel();

    abstract public int getCollectionLevel();

    @Override
    public Integer getNextTraceIndex() {
        return null;
    }
}
