package org.lee.generator.statement;

import org.lee.base.Generator;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.select.SelectStatement;

public interface SQLGenerator extends Generator<SQLStatement> {
    SelectStatement generateSelect();
//    InsertStatement generate
}
