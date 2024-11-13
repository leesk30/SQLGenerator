package org.lee.sql.clause.predicate;

import org.lee.base.NodeTag;
import org.lee.entry.complex.Filter;
import org.lee.sql.clause.Clause;
import org.lee.sql.select.SelectStatement;

public class ConnectByClause extends Clause<Filter> {
    public ConnectByClause(SelectStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.connectByClause;
    }

    @Override
    public void fuzz() {

    }
}
