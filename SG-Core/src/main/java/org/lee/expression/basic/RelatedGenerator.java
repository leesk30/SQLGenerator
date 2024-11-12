package org.lee.expression.basic;

import org.lee.entry.FieldReference;
import org.lee.expression.Expression;
import org.lee.expression.statistic.RelatedStatistic;
import org.lee.statement.support.SQLStatement;

import java.util.List;

public abstract class RelatedGenerator<T extends Expression>
        extends AbstractExpressionGenerator<T> {

    protected final RelatedStatistic statistic;

    protected RelatedGenerator(SQLStatement stmt, List<FieldReference> lhs, List<FieldReference>  rhs){
        super(stmt);
        statistic = new RelatedStatistic(this, lhs, rhs);
    }

    @Override
    public RelatedStatistic getStatistic() {
        return statistic;
    }
}
