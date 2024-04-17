package org.lee.context.decription;

import org.lee.common.Parameter;
import org.lee.context.grammar.Grammar;

public interface SQLContext{
    Grammar getGrammar();
    Parameter getParameter();
    int getRecursionLevel();
    int getCollectionLevel();
    Integer getNextTraceIndex();
}
