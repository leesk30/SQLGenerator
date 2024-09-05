package org.lee.fuzzer;

import org.lee.statement.SQLStatement;
import org.lee.statement.select.SelectStatement;

public class TestSingleSQLGenerator implements Generator<SelectStatement> {
    @Override
    public SelectStatement generate() {
        SelectStatement statement = SelectStatement.randomlyGetStatement();
        statement.fuzz();
        return statement;
    }
}
