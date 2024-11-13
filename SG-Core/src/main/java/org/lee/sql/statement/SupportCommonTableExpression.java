package org.lee.sql.support;

import org.lee.sql.entry.relation.CTE;
import org.lee.sql.clause.from.WithClause;
import org.lee.sql.statement.SQLStatement;

import java.util.List;

public interface SupportCommonTableExpression extends SQLStatement {
    List<CTE> getCTEs();
    WithClause getWithClause();
}
