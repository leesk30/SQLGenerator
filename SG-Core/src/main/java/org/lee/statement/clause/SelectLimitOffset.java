package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.literal.Literal;
import org.lee.statement.node.NodeTag;

import java.util.Iterator;

public class SelectLimitOffset extends LimitOffset {

    public SelectLimitOffset(SQLStatement statement) {
        super(statement);
    }

    public SelectLimitOffset(SQLStatement statement, int initialCapacity) {
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
    public void fuzz() {

    }

    @Override
    public Iterator<Literal<Number>> walk() {
        return null;
    }
}
