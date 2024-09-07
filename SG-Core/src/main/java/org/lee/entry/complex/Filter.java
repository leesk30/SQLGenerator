package org.lee.entry.complex;

import org.lee.fuzzer.Fuzzer;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.IExpression;
import org.lee.statement.expression.Qualification;
import org.lee.node.NodeTag;
import org.lee.node.TreeNode;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;
import org.lee.util.ListUtil;
import org.lee.util.FuzzUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Filter implements Scalar, TreeNode<IExpression>, Fuzzer {
    private final List<IExpression> rawQualifications = new ArrayList<>();
    private IExpression combinedQualifications;
    public Filter(){

    }

    @Override
    public TypeTag getType() {
        return null;
    }

    @Override
    public String getString() {
        return combinedQualifications.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public List<IExpression> getChildNodes() {
        return combinedQualifications.getChildNodes();
    }

    @Override
    public Iterator<IExpression> walk() {
        return null;
    }

    public void put(Qualification qualification){
        rawQualifications.add(qualification);
    }

    @Override
    public void fuzz() {
        assert !rawQualifications.isEmpty();
        List<IExpression> merged = rawQualifications;
        while (merged.size() > 1){
            merged = randomlyMerge(merged);
        }
        assert merged.size() == 1;
        combinedQualifications = merged.get(0);
    }

    private List<IExpression> randomlyMerge(List<IExpression> qualifications){
        if(qualifications.size() < 2){
            return qualifications;
        }
        if(qualifications.size() == 2){
            return Collections.singletonList(combine(qualifications.get(0), qualifications.get(1)));
        }
        final List<IExpression> result = new ArrayList<>();
        final List<IExpression> template = ListUtil.copyListShuffle(qualifications);
        final int splitIndex = FuzzUtil.randomIntFromRange(1, template.size() - 1);
        final List<IExpression> lhs = template.subList(0, splitIndex);
        final List<IExpression> rhs = template.subList(splitIndex, template.size());
        result.addAll(randomlyMerge(lhs));
        result.addAll(randomlyMerge(rhs));
        return result;
    }

    private IExpression combine(IExpression left, IExpression right){
        Qualification.Builder builder = new Qualification.Builder();
        final AtomicInteger counter = new AtomicInteger(0);
        java.util.function.Function<IExpression, IExpression> tryToNegative = (qual) -> {
            if(qual instanceof Qualification && FuzzUtil.probability(3 - counter.get())){
                counter.set(counter.get() + 1);
                return ((Qualification) qual).toNegative();
            }else {
                return qual;
            }
        };
        builder.setCurrent(FuzzUtil.probability(50) ? StaticSymbol.AND:StaticSymbol.OR)
                .addChild(tryToNegative.apply(left))
                .addChild(tryToNegative.apply(right));;
        return tryToNegative.apply(builder.build());
    }
}
