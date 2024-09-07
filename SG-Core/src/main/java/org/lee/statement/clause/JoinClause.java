package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.entry.RangeTableReference;
import org.lee.util.FuzzUtil;

public abstract class JoinClause extends Clause<RangeTableReference> {
    public JoinClause(SQLStatement statement) {
        super(statement, 2);
    }

    public static enum Pattern {
        LEFT,
        INNER,
        RIGHT,
        NATUAL
    }

    protected Pattern getRandomJoinPattern(){
        return FuzzUtil.randomlyChooseFrom(new Pattern[]{Pattern.LEFT, Pattern.RIGHT, Pattern.INNER});
    }

}
