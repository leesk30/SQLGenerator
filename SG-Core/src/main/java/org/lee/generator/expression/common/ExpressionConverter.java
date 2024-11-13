package org.lee.expression.common;

import org.lee.expression.Expression;
import org.lee.expression.basic.IExpressionGenerator;
import org.lee.expression.statistic.GeneratorStatistic;
import org.lee.expression.statistic.UnrelatedStatistic;
import org.lee.statement.support.SQLStatement;

public class ExpressionConverter implements IExpressionGenerator<Expression> {
    private final SQLStatement statement;
    private final UnrelatedStatistic statistic;
    private final Location location;
    public ExpressionConverter(Location location, SQLStatement statement, UnrelatedStatistic statistic){
        this.statement = statement;
        this.statistic = statistic;
        this.location = location;
    }

    @Override
    public Expression generate() {
        return null;
    }

    @Override
    public GeneratorStatistic getStatistic() {
        return statistic;
    }

    @Override
    public Location getExpressionLocation() {
        return location;
    }

    @Override
    public SQLStatement retrieveParent() {
        return statement;
    }
}
