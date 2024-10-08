package org.lee.entry.complex;

import org.lee.base.Fuzzer;
import org.lee.base.NodeTag;
import org.lee.base.TreeNode;
import org.lee.common.Utility;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.PredicateCombiner;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Filter implements Scalar, TreeNode<Qualification>, Fuzzer {
    private final List<Qualification> rawQualifications = new ArrayList<>();
    private Qualification combinedQualifications;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public Filter(){

    }

    @Override
    public TypeTag getType() {
        return null;
    }

    @Override
    public String getString() {
        if(combinedQualifications == null){
            logger.error("combinedQualifications cannot be empty or null");
        }
        return combinedQualifications.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public List<Expression> getChildNodes() {
        return combinedQualifications.getChildNodes();
    }

    @Override
    public Stream<Qualification> walk() {
        return null;
    }

    public void put(Qualification qualification){
        rawQualifications.add(qualification);
    }

    @Override
    public void fuzz() {
        assert !rawQualifications.isEmpty();
        List<Qualification> merged = rawQualifications;
        while (merged.size() > 1){
            merged = randomlyMerge(merged);
        }
        assert merged.size() == 1;
        combinedQualifications = merged.get(0);
    }

    public boolean isEmpty(){
        return rawQualifications.isEmpty();
    }

    private List<Qualification> randomlyMerge(List<Qualification> qualifications){
        if(qualifications.size() < 2){
            return qualifications;
        }
        if(qualifications.size() == 2){
            return Collections.singletonList(combine(qualifications.get(0), qualifications.get(1)));
        }
        final List<Qualification> result = new ArrayList<>();
        final List<Qualification> template = Utility.copyListShuffle(qualifications);
        final int splitIndex = Utility.randomIntFromRange(1, template.size() - 1);
        final List<Qualification> lhs = template.subList(0, splitIndex);
        final List<Qualification> rhs = template.subList(splitIndex, template.size());
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
}
