package org.lee.sql.clause.predicate;

import org.lee.base.Generator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.clause.Clause;
import org.lee.sql.entry.complex.Filter;
import org.lee.sql.expression.Expression;
import org.lee.sql.expression.IExpression;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

public abstract class PredicateClause extends Clause<IExpression<Expression>> {
    protected final Filter filter = new Filter();
    protected final ExpressionLocation predicateLocation;
    protected PredicateClause(ExpressionLocation predicateLocation, SQLStatement statement) {
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
