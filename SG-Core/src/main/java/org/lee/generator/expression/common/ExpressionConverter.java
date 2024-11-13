package org.lee.generator.expression.common;

import org.lee.generator.expression.basic.IExpressionGenerator;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.generator.expression.statistic.UnrelatedStatistic;
import org.lee.sql.expression.Expression;
import org.lee.sql.statement.SQLStatement;

public class ExpressionConverter implements IExpressionGenerator<Expression> {
    private final SQLStatement statement;
    private final UnrelatedStatistic statistic;
    private final ExpressionLocation location;
    public ExpressionConverter(ExpressionLocation location, SQLStatement statement, UnrelatedStatistic statistic){
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
    public ExpressionLocation getExpressionLocation() {
        return location;
    }

    @Override
    public SQLStatement retrieveParent() {
        return statement;
    }
}
