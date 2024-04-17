package org.lee.context.decription;

import org.lee.common.Parameter;
import org.lee.context.grammar.Grammar;

public class SelectContext implements SQLContext {
    @Override
    public Grammar getGrammar() {
        return null;
    }

    @Override
    public Parameter getParameter() {
        return null;
    }

    @Override
    public int getRecursionLevel() {
        return 0;
    }

    @Override
    public int getCollectionLevel() {
        return 0;
    }

    @Override
    public Integer getNextTraceIndex() {
        return null;
    }
}
