package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.*;
import org.lee.entry.relation.CTE;
import org.lee.entry.scalar.Scalar;
import org.lee.entry.literal.Literal;
import org.lee.node.NodeTag;
import org.lee.statement.support.Sortable;
import org.lee.statement.support.SupportCommonTableExpression;

import java.util.List;

public class AbstractNormalSelectStatement extends AbstractSimpleSelectStatement implements Sortable, SupportCommonTableExpression {
    protected WithClause withClause = new WithClause(this);
    protected SortByClause sortByClause = new SelectOrderByClause(this);
    protected LimitOffset limitOffset = new SelectLimitOffset(this);

    public AbstractNormalSelectStatement(SelectType selectType) {
        this(selectType, null);
    }

    public AbstractNormalSelectStatement(SelectType selectType, SQLStatement parent) {
        super(selectType, parent);
        addClause(withClause);
        addClause(sortByClause);
        addClause(limitOffset);
    }

    @Override
    public boolean isScalar() {
        return false;
    }

    public Clause<CTE> getWithClause() {
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
