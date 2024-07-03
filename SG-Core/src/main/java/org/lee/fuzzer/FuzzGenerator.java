package org.lee.fuzzer;

import org.lee.statement.SQLStatement;

public interface FuzzGenerator {
    default void generate(SQLStatement statement){
        prepare(statement);
        fuzzy(statement);
        finish(statement);
    }

    void fuzzy(SQLStatement statement);
    void prepare(SQLStatement statement);
    void finish(SQLStatement statement);
}

