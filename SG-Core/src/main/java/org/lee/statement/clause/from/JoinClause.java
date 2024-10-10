package org.lee.statement.clause.from;

import org.lee.common.Utility;
import org.lee.entry.RangeTableReference;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.SQLStatement;

public abstract class JoinClause extends Clause<RangeTableReference> {
    protected final Pattern pattern = Utility.randomlyChooseFrom(new Pattern[]{Pattern.LEFT, Pattern.RIGHT, Pattern.INNER});
    public JoinClause(SQLStatement statement) {
        super(statement, 2);
    }

    public enum Pattern {
        LEFT,
        INNER,
        RIGHT,
        NATUAL
    }

}
