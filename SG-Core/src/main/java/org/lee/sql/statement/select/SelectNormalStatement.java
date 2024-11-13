package org.lee.sql.select;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.relation.CTE;
import org.lee.sql.clause.from.WithClause;
import org.lee.sql.clause.limit.LimitOffset;
import org.lee.sql.clause.limit.SelectLimitOffset;
import org.lee.sql.clause.sort.SelectOrderByClause;
import org.lee.sql.clause.sort.SortByClause;
import org.lee.sql.support.SQLStatement;
import org.lee.sql.support.Sortable;
import org.lee.sql.support.SupportCommonTableExpression;

import java.util.List;

public class SelectNormalStatement
        extends AbstractSimpleSelectStatement
        implements Sortable, SupportCommonTableExpression {
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
        if(width() != 1){
            return false;
        }
        if(getLimitOffset().getLimit() == 1){
            return true;
        }
        TargetEntry firstEntry = targetList.getChildNodes().get(0);
        return firstEntry.isScalarStyle();
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
