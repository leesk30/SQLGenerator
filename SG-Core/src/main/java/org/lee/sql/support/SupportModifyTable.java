package org.lee.sql.support;

import org.lee.sql.clause.modify.ModifyTableClause;
import org.lee.sql.statement.SQLStatement;

public interface SupportModifyTable extends SQLStatement {
    ModifyTableClause getModifyTableClause();
}
