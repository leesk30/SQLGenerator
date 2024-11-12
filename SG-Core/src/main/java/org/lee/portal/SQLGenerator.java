package org.lee.portal;

import org.lee.base.Generator;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.support.SQLStatement;

public interface SQLGenerator extends Generator<SQLStatement> {
    SelectStatement generateSelect();
//    InsertStatement generate
}
