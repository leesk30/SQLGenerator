package org.lee.statement.expression.abs;

import org.lee.entry.FieldReference;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.statistic.RelatedStatistic;
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
