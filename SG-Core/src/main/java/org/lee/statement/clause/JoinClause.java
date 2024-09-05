package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.RangeTableReference;

public abstract class JoinClause extends Clause<RangeTableReference> {
    public JoinClause(SQLStatement statement) {
        super(statement);
    }

    public JoinClause(SQLStatement statement, int childrenInitialCapacity) {
        super(statement, childrenInitialCapacity);
    }

    public static enum Pattern {
        LEFT,
        INNER,
        RIGHT,
        NATUAL
    }
}
