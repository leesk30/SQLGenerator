package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.node.NodeTag;
import org.lee.statement.select.SelectStatement;

import java.util.Iterator;
import java.util.List;

public class GroupByClause extends Clause<Scalar> {
    public GroupByClause(SelectStatement statement) {
        super(statement);
    }

    public GroupByClause(SelectStatement statement, int initialCapacity) {
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
    public List<Scalar> getChildNodes() {
        return null;
    }

    @Override
    public Iterator<Scalar> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
