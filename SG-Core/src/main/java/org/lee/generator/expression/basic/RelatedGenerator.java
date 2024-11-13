package org.lee.generator.expression.basic;

import org.lee.generator.expression.statistic.RelatedStatistic;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.expression.Expression;
import org.lee.sql.statement.SQLStatement;

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
