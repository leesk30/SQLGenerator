package org.lee.generator.expression.basic;


import org.lee.common.NamedLoggers;
import org.lee.common.exception.ValueCheckFailedException;
import org.lee.common.generator.Generator;
import org.lee.common.generator.WeightedAccessor;
import org.lee.common.structure.Weighted;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractWeightedExpressionGenerator<T extends Expression>
        extends AbstractExpressionGenerator<T>
            implements IExpressionGenerator<T> {
    private final static Logger LOGGER = NamedLoggers.getCoreLogger(AbstractWeightedExpressionGenerator.class);

    private final WeightedMethodInvoker<T> invoker = new WeightedMethodInvoker<>();

    protected AbstractWeightedExpressionGenerator(
            ExpressionLocation expressionLocation, SQLStatement statement, GeneratorStatistic statistic
    ) {
        super(expressionLocation, statement, statistic);
        onInitWeightedMethodInvoker();
    }

    protected AbstractWeightedExpressionGenerator(ExpressionLocation expressionLocation, SQLStatement statement) {
        super(expressionLocation, statement);
        onInitWeightedMethodInvoker();
    }

    protected AbstractWeightedExpressionGenerator(
            ExpressionLocation expressionLocation, SQLStatement statement, List<? extends Scalar> scalars
    ) {
        super(expressionLocation, statement, scalars);
        onInitWeightedMethodInvoker();
    }

    protected AbstractWeightedExpressionGenerator(
            ExpressionLocation expressionLocation, SQLStatement statement,
            List<? extends Scalar> left, List<? extends Scalar> right
    ) {
        super(expressionLocation, statement, left, right);
        onInitWeightedMethodInvoker();
    }


    protected abstract void onInitWeightedMethodInvoker();

    public List<Weighted<Supplier<T>>> getWeightedMethods(){
        LOGGER.error("No configuration for weighted route");
        return Collections.emptyList();
    }

    public final void registerMethod(Supplier<T> method, int weight){
        this.invoker.add(method, weight);
    }

    public final void registerMethod(Weighted<Supplier<T>> method){
        this.invoker.add(method);
    }

    protected final void registerMethods(List<Weighted<Supplier<T>>> methods){
        for(Weighted<Supplier<T>> method: methods)
            this.invoker.add(method);
    }

    public Generator<T> getInvoker() {
        return invoker;
    }

    @Override
    public T generate() {
        if(invoker.isEmpty()){
            LOGGER.warn("Fallback due to empty invoker for class: " + this.getClass().getName());
            return fallback();
        }
        T result = invoker.generate();
        return result == null ? fallback() : result;
    }

    private static class WeightedMethodInvoker<T extends Expression> extends WeightedAccessor<Supplier<T>, T> {
        private final static int DEFAULT_WEIGHT = 100;

        public WeightedMethodInvoker(){
            super(DEFAULT_WEIGHT);
        }

        @Override
        protected T onHookedGenerate() {
            return get().get();
        }

        public boolean isEmpty(){
            return wrappers.isEmpty();
        }
    }

}
