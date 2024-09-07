package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.entry.complex.Filter;
import org.lee.node.NodeTag;

import java.util.Iterator;

public class WhereClause extends Clause<Filter> {
    public WhereClause(SQLStatement statement) {
        super(statement);
    }

    public WhereClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        return "WHERE " + nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.whereClause;
    }

    @Override
    public Iterator<Filter> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
