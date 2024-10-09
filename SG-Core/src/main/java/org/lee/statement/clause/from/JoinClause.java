package org.lee.statement.clause.from;

import org.lee.common.Utility;
import org.lee.entry.RangeTableReference;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.Clause;

public abstract class JoinClause extends Clause<RangeTableReference> {
    public JoinClause(SQLStatement statement) {
        super(statement, 2);
    }

    public enum Pattern {
        LEFT,
        INNER,
        RIGHT,
        NATUAL
    }

    protected Pattern getRandomJoinPattern(){
        return Utility.randomlyChooseFrom(new Pattern[]{Pattern.LEFT, Pattern.RIGHT, Pattern.INNER});
    }

}
