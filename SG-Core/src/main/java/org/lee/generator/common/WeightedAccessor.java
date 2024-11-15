package org.lee.generator.common;

import org.lee.base.Generator;
import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.exception.InternalError;
import org.lee.common.exception.UnreachableError;
import org.lee.common.exception.ValueCheckFailedException;
import org.lee.common.structure.Weighted;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.expression.Expression;
import org.lee.sql.symbol.Comparator;
import org.lee.sql.symbol.Symbol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class WeightedAccessor<IN, OUT> implements Generator<OUT> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeightedAccessor.class);

    protected final List<Weighted<IN>> wrappers = new ArrayList<>();
    protected final int defaultWeight;

    protected int sum = 0;
    protected boolean isNewest = false;
    protected boolean isSameWeight = true;

    protected int total = 0;

    private AccessPolicy policy = AccessPolicy.normal;

    private enum AccessPolicy{
        normal,
        balance,
        singleton,
    }

    protected WeightedAccessor(int defaultWeight){
        this.defaultWeight = defaultWeight;
    }

    public final OUT generate(){
        // TODO: record hit rate
        total ++;
        return onHookedGenerate();
    }

    abstract protected OUT onHookedGenerate();

    public void add(IN weighted){
        add(weighted, defaultWeight);
    }

    public void add(IN weighted, int weight){
        wrappers.add(new Weighted<IN>(weighted, weight));
        sum += weight;
        isNewest = false;

        if(isSameWeight && wrappers.size() >= 2){
            isSameWeight = weight == wrappers.get(wrappers.size() - 2).weight();
        }
    }

    protected void add(Weighted<IN> weighted){
        wrappers.add(weighted);
        sum += weighted.weight();
        isNewest = false;

        if(isSameWeight && wrappers.size() >= 2){
            isSameWeight = weighted.weight() == wrappers.get(wrappers.size() - 2).weight();
        }
    }


    void flushPolicy(){
        Assertion.requiredFalse(wrappers.isEmpty());
        if(wrappers.size() == 1){
            policy = AccessPolicy.singleton;
        }
    }

    /**
     * GET by Weight
     * */
    protected final IN get(){
        if(!isNewest){
            flushPolicy();
            Collections.shuffle(wrappers);
            isNewest = true;
        }

        if(policy == AccessPolicy.singleton){
            return wrappers.get(0).get();
        } else if (policy == AccessPolicy.balance) {
            return Utility.randomlyChooseFrom(wrappers).get();
        }

        int value = Utility.randomIntFromRange(0, sum);
        for(Weighted<IN> weightedElement: wrappers){
            value = value - weightedElement.weight();
            if(value <= 0){
                return weightedElement.get();
            }
        }
        // cannot
        throw new UnreachableError();
    }


    public static <T extends Expression> WeightedAccessor<Method, T> getInvoker(Generator<T> generator){
        return new Invoker<>(generator);
    }

    public static <T extends Expression> Combiner<T> getCombiner(int defaultWeight){
        return new Combiner<>(defaultWeight);
    }

    private static class Invoker<T extends Expression> extends WeightedAccessor<Method, T> {
        private final static int DEFAULT_WEIGHT = 100;
        private final Generator<T> generator;
        private final Set<Method> methodSet;

        public Invoker(Generator<T> generator){
            super(DEFAULT_WEIGHT);
            this.generator = generator;
            this.methodSet = new HashSet<>(Arrays.asList(this.generator.getClass().getMethods()));
        }

        @Override
        public void add(Method weighted) {
            if(!methodSet.contains(weighted)){
                throw new ValueCheckFailedException("The method doesn't exists");
            }
            super.add(weighted);
        }

        @Override
        public void add(Method weighted, int weight) {
            if(!methodSet.contains(weighted)){
                throw new ValueCheckFailedException("The method doesn't exists");
            }
            super.add(weighted, weight);
        }

        @Override
        protected T onHookedGenerate() {
            Method method = null;
            try {
                method = get();
                return (T) method.invoke(generator);
            } catch (IllegalAccessException e) {
                throw new InternalError(e);
            }  catch (InvocationTargetException e){
                LOGGER.error("Invoke method failed:" + method.getName(), e);
                throw new InternalError(e);
            }
        }
    }

    public static class Combiner<T extends Expression> extends WeightedAccessor<Generator<T>, T> {

        public Combiner(int defaultWeight){
            super(defaultWeight);
        }

        @Override
        protected T onHookedGenerate() {
            return get().generate();
        }
    }

    public static class WeightedSymbolAccessor<T extends Symbol> extends WeightedAccessor<T, T> {

        public WeightedSymbolAccessor(int defaultWeight) {
            super(defaultWeight);
        }

        @Override
        protected T onHookedGenerate() {
            return get();
        }
    }

    public static class ComparatorAccessor extends WeightedSymbolAccessor<Comparator>{

        private final ExpressionLocation location;
        public ComparatorAccessor(ExpressionLocation location) {
            super(Comparator.DEFAULT_WEIGHT);
            this.location = location;
        }

        @Override
        public void add(Comparator weighted) {
            add(weighted, weighted.getWeight(location));
        }
    }

}
