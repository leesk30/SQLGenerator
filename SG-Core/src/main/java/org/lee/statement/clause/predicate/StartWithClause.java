package org.lee.statement.clause.predicate;

import org.lee.base.NodeTag;
import org.lee.entry.complex.Filter;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectStatement;

public class StartWithClause extends Clause<Filter> {
    public StartWithClause(SelectStatement statement) {
        super(statement);
    }

    public StartWithClause(SelectStatement statement, int initialCapacity) {
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
