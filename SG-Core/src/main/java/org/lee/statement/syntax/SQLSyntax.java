package org.lee.statement.syntax;

import org.lee.fuzzer.Fuzzer;
import org.lee.statement.SQLStatement;
import org.lee.statement.SQLType;
import org.lee.statement.select.SelectStatement;

public abstract class SQLSyntax implements Fuzzer {
    protected final SQLStatement statement;

    protected boolean enableCTE;
    protected boolean isModifyTable;
    protected SQLSyntax(SQLStatement statement){
        this.statement = statement;
    }

    public static SQLSyntax newSyntax(SQLStatement statement){
        SQLType sqlType = statement.getSqlType();
        switch (sqlType){
            case select:
                return new SelectSyntax((SelectStatement) statement);
            default:
                throw new RuntimeException("Not support create syntax for sql type: " + sqlType);
        }
    }

}
