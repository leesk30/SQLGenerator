package org.lee.generator.expression.basic;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.statement.SQLStatement;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

public abstract class AbstractExpressionGenerator<T extends Expression>
        implements IExpressionGenerator<T> {

    protected final static Logger LOGGER = NamedLoggers.getCoreLogger(AbstractExpressionGenerator.class);
    protected final SQLStatement statement;
    protected final RuntimeConfiguration config;
    protected final GeneratorStatistic statistic;
    protected final ExpressionLocation expressionLocation;

    protected AbstractExpressionGenerator(SQLStatement statement){
        this(ExpressionLocation.unknown, statement);
    }

    protected AbstractExpressionGenerator(ExpressionLocation expressionLocation, SQLStatement statement, GeneratorStatistic statistic){
        Assertion.requiredNonNull(statistic);
        this.statement = statement;
        if(statement != null){
            this.config = statement.getConfig();
        }else {
            this.config = IExpressionGenerator.super.getConfig();
        }
        this.statistic = statistic;
        this.expressionLocation = expressionLocation;
    }

    protected AbstractExpressionGenerator(ExpressionLocation expressionLocation, SQLStatement statement){
        this(expressionLocation, statement, Collections.emptyList(), Collections.emptyList());
    }

    protected AbstractExpressionGenerator(ExpressionLocation expressionLocation, SQLStatement statement, List<? extends Scalar> scalars){
        this(expressionLocation, statement, scalars, Collections.emptyList());
    }


    protected AbstractExpressionGenerator(ExpressionLocation expressionLocation, SQLStatement statement, List<? extends Scalar> left, List<? extends Scalar> right){
        this.statement = statement;
        if(statement != null){
            this.config = statement.getConfig();
        }else {
            this.config = IExpressionGenerator.super.getConfig();
        }
        this.expressionLocation = expressionLocation;
        if(left.isEmpty() && right.isEmpty()){
            statistic = GeneratorStatistic.create();
        }else if(left.isEmpty()){
            statistic = GeneratorStatistic.create(right);
        } else if (right.isEmpty()) {
            statistic = GeneratorStatistic.create(left);
        }else {
            statistic = GeneratorStatistic.create(left, right);
        }
        Assertion.requiredNonNull(statistic);
    }

    @Override
    public Logger getLogger(){
        return LOGGER;
    }

    @Override
    public RuntimeConfiguration getConfig(){
        return config;
    }

    @Override
    public SQLStatement retrieveParent() {
        return statement;
    }

    @Override
    public GeneratorStatistic getStatistic() {
        return statistic;
    }

    @Override
    public ExpressionLocation getExpressionLocation() {
        return expressionLocation;
    }
}
