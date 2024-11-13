package org.lee.sql.support;

import org.lee.sql.clause.modify.ModifyTableClause;

public interface SupportModifyTable extends SQLStatement {
    ModifyTableClause getModifyTableClause();
}
