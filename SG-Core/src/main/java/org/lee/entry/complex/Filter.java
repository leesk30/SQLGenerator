package org.lee.entry.complex;

import org.lee.fuzzer.Fuzzer;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.Qualification;
import org.lee.node.NodeTag;
import org.lee.node.TreeNode;
import org.lee.symbol.PredicateCombiner;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;
import org.lee.util.ListUtil;
import org.lee.util.FuzzUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Filter implements Scalar, TreeNode<Qualification>, Fuzzer {
    private final List<Qualification> rawQualifications = new Vector<>();
    private Qualification combinedQualifications;
    public Filter(){

    }

    @Override
    public TypeTag getType() {
        return null;
    }

    @Override
    public String getString() {
        if(combinedQualifications == null){
            System.out.println("=================!!!!!===================");
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
        final List<Qualification> template = ListUtil.copyListShuffle(qualifications);
        final int splitIndex = FuzzUtil.randomIntFromRange(1, template.size() - 1);
        final List<Qualification> lhs = template.subList(0, splitIndex);
        final List<Qualification> rhs = template.subList(splitIndex, template.size());
        result.addAll(randomlyMerge(lhs));
        result.addAll(randomlyMerge(rhs));
        return result;
    }

    private Qualification combine(Qualification left, Qualification right){
        Qualification qualification = new Qualification(FuzzUtil.probability(50) ? PredicateCombiner.AND:PredicateCombiner.OR);
        final AtomicInteger counter = new AtomicInteger(0);
        java.util.function.Function<Qualification, Qualification> tryToNegative = (qual) -> {
            if(FuzzUtil.probability(3 - counter.get())){
                counter.set(counter.get() + 1);
                return qual.toNegative();
            }else {
                return qual;
            }
        };
        return tryToNegative.apply(
                qualification
                        .newChild(tryToNegative.apply(left))
                        .newChild(tryToNegative.apply(right))
        );
    }
}
