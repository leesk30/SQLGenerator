package org.lee.statement.clause;

import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
import org.lee.statement.select.SelectStatement;

import java.util.Iterator;

public class GroupByClause extends Clause<Scalar> {
    public GroupByClause(SelectStatement statement) {
        super(statement);
    }

    public GroupByClause(SelectStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.groupByClause;
    }

    @Override
    public Iterator<Scalar> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
