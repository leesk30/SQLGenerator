package org.lee.portal;

import org.lee.base.Generator;
import org.lee.sql.statement.select.SelectStatement;
import org.lee.sql.statement.basic.SQLStatement;

public interface SQLGenerator extends Generator<SQLStatement> {
    SelectStatement generateSelect();
//    InsertStatement generate
}
