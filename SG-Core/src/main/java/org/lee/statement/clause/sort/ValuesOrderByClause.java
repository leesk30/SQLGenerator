package org.lee.statement.clause.sort;

import org.lee.statement.ValuesStatement;

import java.util.function.IntConsumer;

public class ValuesOrderByClause extends SortByClause {
    public ValuesOrderByClause(ValuesStatement statement) {
        super(statement);
    }

    @Override
    protected IntConsumer getConsumer() {
        // values statement cannot sort by target for some situations
        return orderByIndex();
    }
}
