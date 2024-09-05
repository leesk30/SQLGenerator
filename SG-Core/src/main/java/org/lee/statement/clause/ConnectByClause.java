package org.lee.statement.clause;

import org.lee.statement.complex.Filter;
import org.lee.statement.node.NodeTag;
import org.lee.statement.select.SelectStatement;

import java.util.Iterator;
import java.util.List;

public class ConnectByClause extends Clause<Filter> {
    public ConnectByClause(SelectStatement statement) {
        super(statement);
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
