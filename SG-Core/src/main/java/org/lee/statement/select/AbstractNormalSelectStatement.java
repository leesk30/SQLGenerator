package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.*;
import org.lee.entry.relation.CTE;
import org.lee.entry.scalar.Scalar;
import org.lee.entry.literal.Literal;
import org.lee.node.NodeTag;
import org.lee.statement.support.Sortable;

public class AbstractNormalSelectStatement extends AbstractSimpleSelectStatement implements Sortable {
    protected WithClause withClause = new WithClause(this);
    protected SortByClause sortByClause = new SelectOrderByClause(this);
    protected LimitOffset limitOffset = new SelectLimitOffset(this);

    public AbstractNormalSelectStatement(SelectType selectType) {
        this(selectType, null);
    }

    public AbstractNormalSelectStatement(SelectType selectType, SQLStatement parent) {
        super(selectType, parent);
        this.childrenMap.put(NodeTag.withClause, withClause);
        this.childrenMap.put(NodeTag.sortByClause, sortByClause);
        this.childrenMap.put(NodeTag.limitOffset, limitOffset);
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
        sortByClause.fuzz();
        limitOffset.fuzz();
    }
}
