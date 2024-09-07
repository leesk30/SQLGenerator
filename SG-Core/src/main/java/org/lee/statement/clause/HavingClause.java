package org.lee.statement.clause;

import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
import org.lee.statement.select.SelectStatement;

import java.util.Iterator;

public class HavingClause extends Clause<Scalar> {
    public HavingClause(SelectStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.havingClause;
    }

    @Override
    public Iterator<Scalar> walk() {
        return null;
    }

    @Override
    public void fuzz() {

    }
}
