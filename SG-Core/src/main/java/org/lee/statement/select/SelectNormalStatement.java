package org.lee.statement.select;

import org.lee.entry.relation.CTE;
import org.lee.statement.SQLStatement;
import org.lee.statement.clause.from.WithClause;
import org.lee.statement.clause.limit.LimitOffset;
import org.lee.statement.clause.limit.SelectLimitOffset;
import org.lee.statement.clause.sort.SelectOrderByClause;
import org.lee.statement.clause.sort.SortByClause;
import org.lee.statement.support.Sortable;
import org.lee.statement.support.SupportCommonTableExpression;

import java.util.List;

public class SelectNormalStatement extends AbstractSimpleSelectStatement implements Sortable, SupportCommonTableExpression {
    protected final WithClause withClause = new WithClause(this);
    protected final SortByClause sortByClause = new SelectOrderByClause(this);
    protected final LimitOffset limitOffset = new SelectLimitOffset(this);

    public SelectNormalStatement() {
        this(null);
    }

    public SelectNormalStatement(SQLStatement parent) {
        super(SelectType.normal, parent);
        addClause(withClause);
        addClause(sortByClause);
        addClause(limitOffset);
    }

    @Override
    public boolean isScalar() {
        return false;
    }

    public WithClause getWithClause() {
        return withClause;
    }

    public SortByClause getSortByClause() {
        return sortByClause;
    }

    public LimitOffset getLimitOffset() {
        return limitOffset;
    }

    @Override
    public void fuzz() {
        withClause.fuzz();
        fromClause.fuzz();
        targetList.fuzz();
        whereClause.fuzz();
        groupByClause.fuzz();
        havingClause.fuzz();
        sortByClause.fuzz();
        limitOffset.fuzz();
    }

    @Override
    public List<CTE> getCTEs() {
        return withClause.getChildNodes();
    }
}
