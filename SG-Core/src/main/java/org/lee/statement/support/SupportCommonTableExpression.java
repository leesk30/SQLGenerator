package org.lee.statement.support;

import org.lee.entry.relation.CTE;
import org.lee.statement.clause.from.WithClause;

import java.util.List;

public interface SupportCommonTableExpression extends SQLStatement {
    List<CTE> getCTEs();
    WithClause getWithClause();
}
