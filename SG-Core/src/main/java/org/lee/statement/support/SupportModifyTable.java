package org.lee.statement.support;

import org.lee.statement.clause.modify.ModifyTableClause;

public interface SupportModifyTable extends SQLStatement {
    ModifyTableClause getModifyTableClause();
}
