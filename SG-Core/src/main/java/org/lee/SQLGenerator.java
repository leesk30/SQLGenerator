package org.lee;

import org.lee.common.exception.Assertion;
import org.lee.base.Generator;
import org.lee.statement.SQLStatement;
import org.lee.statement.SQLType;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;

public class SQLGenerator implements Generator<SQLStatement> {
    protected final SQLType sqlType;
    protected final SelectType requiredSelectType;
    protected SQLGenerator(SQLType sqlType){
        this.sqlType = sqlType;
        this.requiredSelectType = null;
    }

    private SQLStatement getStatement(){
        switch (sqlType){
            case select:
                return SelectStatement.getStatementBySelectType(requiredSelectType);
            case values:
            case update:
            case insert:
            case delete:
            case merge:
            default:
                return null;
        }
    }

    @Override
    public SQLStatement generate() {
        SQLStatement statement = getStatement();
        Assertion.requiredNonNull(statement);
        assert statement != null;
        statement.fuzz();
        return statement;
    }

}