package org.lee.statement.clause;

public interface JoinClause extends Clause{
    static enum JoinPattern{
        LEFT,
        INNER,
        RIGHT,
        NATUAL
    }
}
