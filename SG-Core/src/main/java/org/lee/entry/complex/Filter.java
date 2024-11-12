package org.lee.entry.complex;

import org.lee.base.Fuzzer;
import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.entry.Normalized;
import org.lee.entry.scalar.Scalar;
import org.lee.expression.Qualification;
import org.lee.symbol.PredicateCombiner;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Filter extends ArrayList<Qualification> implements Normalized<Qualification>, Scalar, Fuzzer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Filter.class);
    private Qualification combined = null;
    public Filter(){}

    @Override
    public TypeTag getType() {
        return TypeTag.boolean_;
    }

    @Override
    public String getString() {
        if(combined == null){
            LOGGER.error("The filter combined result cannot be empty or null");
        }
        return combined.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.filter;
    }

    @Override
    public void fuzz() {
        assert !this.isEmpty();
        List<Qualification> merged = new ArrayList<>(this);
        while (merged.size() > 1){
            merged = randomlyMerge(merged);
        }
        assert merged.size() == 1;
        combined = merged.get(0);
    }

    private List<Qualification> randomlyMerge(List<Qualification> qualifications){
        if(qualifications.size() < 2){
            return qualifications;
        }
        if(qualifications.size() == 2){
            return Collections.singletonList(combine(qualifications.get(0), qualifications.get(1)));
        }
        final List<Qualification> result = new ArrayList<>();
        Collections.shuffle(qualifications);
        final int splitIndex = Utility.randomIntFromRange(1, qualifications.size() - 1);
        final List<Qualification> lhs = qualifications.subList(0, splitIndex);
        final List<Qualification> rhs = qualifications.subList(splitIndex, qualifications.size());
        result.addAll(randomlyMerge(lhs));
        result.addAll(randomlyMerge(rhs));
        return result;
    }

    private Qualification combine(Qualification left, Qualification right){
        Qualification qualification = new Qualification(Utility.probability(50) ? PredicateCombiner.AND : PredicateCombiner.OR);
        final AtomicInteger counter = new AtomicInteger(0);
        java.util.function.Function<Qualification, Qualification> tryToNegative = (qual) -> {
            if(Utility.probability(3 - counter.get())){
                counter.set(counter.get() + 1);
                return qual.toNegative();
            }else {
                return qual;
            }
        };
        return tryToNegative.apply(
                qualification.newChild(tryToNegative.apply(left))
                        .newChild(tryToNegative.apply(right))
        );
    }

    @Override
    public Qualification getWrapped() {
        return combined;
    }
}
