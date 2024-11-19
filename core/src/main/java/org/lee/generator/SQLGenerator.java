package org.lee.generator;

import org.lee.common.generator.Generator;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.statement.select.SelectStatement;

public interface SQLGenerator extends Generator<SQLStatement> {
    SelectStatement generateSelect();
//    InsertStatement generate
}
