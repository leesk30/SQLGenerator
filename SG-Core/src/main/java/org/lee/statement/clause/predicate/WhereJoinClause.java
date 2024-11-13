package org.lee.statement.clause.predicate;

import org.apache.commons.lang3.StringUtils;
import org.lee.entry.RangeTableReference;
import org.lee.statement.support.SQLStatement;

public class WhereJoinClause extends JoinClause{

    public WhereJoinClause(SQLStatement statement, RangeTableReference left, RangeTableReference right) {
        super(statement, left, right);
    }

    @Override
    public String getString() {
        return StringUtils.joinWith(" ", left.getString(), pattern.toString(), JOIN, right.getString(), ON, filter.getString());
    }
}
