package org.lee.sql.clause.predicate;

import org.lee.base.NodeTag;
import org.lee.entry.scalar.Scalar;
import org.lee.sql.clause.Clause;
import org.lee.sql.select.SelectStatement;

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
