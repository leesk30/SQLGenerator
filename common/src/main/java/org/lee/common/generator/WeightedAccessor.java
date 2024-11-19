package org.lee.common.generator;

import org.lee.common.Assertion;
import org.lee.common.NamedLoggers;
import org.lee.common.exception.UnreachableError;
import org.lee.common.structure.Weighted;
import org.lee.common.utils.RandomUtils;
import org.slf4j.Logger;

import java.util.*;

public abstract class WeightedAccessor<IN, OUT> implements Generator<OUT> {
    private static final Logger LOGGER = NamedLoggers.getCommonLogger(WeightedAccessor.class);

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
            return RandomUtils.randomlyChooseFrom(wrappers).get();
        }

        int value = RandomUtils.randomIntFromRange(0, sum);
        for(Weighted<IN> weightedElement: wrappers){
            value = value - weightedElement.weight();
            if(value <= 0){
                return weightedElement.get();
            }
        }
        // cannot
        throw new UnreachableError();
    }

}
