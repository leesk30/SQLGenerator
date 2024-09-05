package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.complex.Filter;
import org.lee.statement.node.NodeTag;

import java.util.Iterator;
import java.util.List;

public class StartWithClause extends Clause<Filter> {
    public StartWithClause(SQLStatement statement) {
        super(statement);
    }

    public StartWithClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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
    public List<Filter> getChildNodes() {
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
