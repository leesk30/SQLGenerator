package org.lee.statement.select;

import org.lee.statement.SQLStatement;
import org.lee.statement.clause.*;
import org.lee.statement.entry.relation.CTE;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.entry.literal.Literal;
import org.lee.statement.node.Node;

import java.util.Iterator;

public class AbstractNormalSelectStatement extends AbstractSimpleSelectStatement{
    protected WithClause withClause = new WithClause(this);
    protected SortByClause sortByClause = new SortByClause(this);
    protected LimitOffset limitOffset = new SelectLimitOffset(this);

    public AbstractNormalSelectStatement(SelectType selectType) {
        super(selectType);
        this.children.add(withClause);
        this.children.add(sortByClause);
        this.children.add(limitOffset);
    }

    public AbstractNormalSelectStatement(SelectType selectType, SQLStatement parent) {
        super(selectType, parent);
    }

    @Override
    public boolean isScalar() {
        return false;
    }

    @Override
    public String getString() {
        StringBuilder builder = new StringBuilder();
        Iterator<Clause<? extends Node>> it = this.walk();
        while (it.hasNext())
            builder.append(it.next().getString());
        return builder.toString();
    }

    public Clause<CTE> getWithClause() {
        return withClause;
    }

    public Clause<Scalar> getSortByClause() {
        return sortByClause;
    }

    public Clause<Literal<Number>> getLimitOffset() {
        return limitOffset;
    }

    @Override
    public void fuzz() {

    }
}
