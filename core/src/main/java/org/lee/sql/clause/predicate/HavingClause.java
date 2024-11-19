package org.lee.sql.clause.predicate;

import org.lee.common.enumeration.NodeTag;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.statement.select.SelectStatement;

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
