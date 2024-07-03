package org.lee.fuzzer;

import org.lee.statement.node.Node;
import org.lee.statement.SQLType;
import org.lee.statement.SQLStatement;

public class SQLFuzzer implements Fuzzer{
    private SQLStatement statement;
    public SQLFuzzer(SQLType sqlType){

    }

    @Override
    public Node fuzzy(Node node) {
        return null;
    }

    @Override
    public Fuzzer next() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }
}
