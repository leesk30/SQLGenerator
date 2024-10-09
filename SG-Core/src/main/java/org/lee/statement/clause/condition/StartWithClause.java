package org.lee.statement.clause.condition;

import org.lee.base.NodeTag;
import org.lee.entry.complex.Filter;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;

public class StartWithClause extends Clause<Filter> {
    public StartWithClause(SQLStatement statement) {
        super(statement);
    }

    public StartWithClause(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.startWithClause;
    }

    @Override
    public void fuzz() {

    }
}
