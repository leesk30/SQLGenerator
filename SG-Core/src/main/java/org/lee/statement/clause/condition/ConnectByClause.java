package org.lee.statement.clause.condition;

import org.lee.base.NodeTag;
import org.lee.entry.complex.Filter;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectStatement;

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
