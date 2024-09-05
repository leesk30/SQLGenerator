package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.complex.Filter;
import org.lee.statement.node.NodeTag;

import java.util.Iterator;
import java.util.List;

public class WhereClause extends Clause<Filter> {
    public WhereClause(SQLStatement statement) {
        super(statement);
    }

    public WhereClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public Iterator<Filter> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
