package org.lee.statement.clause.predicate;

import org.lee.base.NodeTag;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectStatement;

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
    public void fuzz() {

    }
}
