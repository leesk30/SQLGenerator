package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.entry.complex.Filter;
import org.lee.node.NodeTag;

import java.util.Iterator;

public class StartWithClause extends Clause<Filter> {
    public StartWithClause(SQLStatement statement) {
        super(statement);
    }

    public StartWithClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.startWithClause;
    }

    @Override
    public Iterator<Filter> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
