package org.lee.statement.expression.common;

import org.lee.statement.expression.Expression;
import org.lee.statement.expression.abs.IExpressionGenerator;
import org.lee.statement.expression.statistic.GeneratorStatistic;
import org.lee.statement.expression.statistic.UnrelatedStatistic;
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
