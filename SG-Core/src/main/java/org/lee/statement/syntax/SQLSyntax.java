package org.lee.statement.syntax;

import org.lee.statement.SQLType;

public abstract class SQLSyntax {
    protected boolean enableCTE;
    protected boolean isModifyTable;
    protected final SQLType sqlType;
    protected SQLSyntax(SQLType sqlType){
        this.sqlType = sqlType;
    }

    public static SQLSyntax newSyntaxController(SQLType sqlType){
        SelectSyntax sqlGrammar;
        switch (sqlType){
            case select:
            case scalar:
            case setop:
            case simple:
                return new SelectSyntax(sqlType);
        }
        return null;
    }
}
