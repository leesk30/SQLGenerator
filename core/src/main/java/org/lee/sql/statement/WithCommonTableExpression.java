package org.lee.sql.statement;

import org.lee.sql.clause.from.WithClause;
import org.lee.sql.entry.relation.CTE;

import java.util.List;

public interface WithCommonTableExpression extends SQLStatement {
    List<CTE> getCTEs();
    WithClause getWithClause();
}
