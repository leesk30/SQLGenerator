package org.lee.fuzzer;

import org.lee.statement.SQLStatement;
import org.lee.statement.SQLType;

public class SQLGenerator implements Generator<SQLStatement> {

    protected SQLGenerator(int generateNum, SQLType sqlType){
    }

    @Override
    public SQLStatement generate() {
        return null;
    }

}
