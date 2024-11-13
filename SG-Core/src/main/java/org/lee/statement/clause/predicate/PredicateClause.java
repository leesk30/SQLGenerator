package org.lee.statement.clause.predicate;

import org.lee.base.Generator;
import org.lee.entry.complex.Filter;
import org.lee.expression.Expression;
import org.lee.expression.IExpression;
import org.lee.expression.Qualification;
import org.lee.expression.common.Location;
import org.lee.statement.clause.Clause;
import org.lee.statement.support.SQLStatement;

public abstract class PredicateClause extends Clause<IExpression<Expression>> {
    protected final Filter filter = new Filter();
    protected final Location predicateLocation;
    protected PredicateClause(Location predicateLocation, SQLStatement statement) {
        super(statement, 1);
        this.children.add(filter);
        this.predicateLocation = predicateLocation;
    }

    @Override
    public String getString() {
        return this.getChildNodes().get(0).getString();
    }

    @Override
    public boolean isEmpty() {
        if(children.isEmpty()){
            return true;
        }
        return children.get(0).getChildNodes().isEmpty();
    }

    public Filter getFilter() {
        return filter;
    }

    protected abstract Generator<Qualification> createPredicateGenerator();
}
