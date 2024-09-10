package org.lee.entry.complex;

import org.lee.fuzzer.Fuzzer;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.Qualification;
import org.lee.node.NodeTag;
import org.lee.node.TreeNode;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;
import org.lee.util.ListUtil;
import org.lee.util.FuzzUtil;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Filter implements Scalar, TreeNode<Expression>, Fuzzer {
    private final List<Expression> rawQualifications = new Vector<>();
    private Expression combinedQualifications;
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
    public List<Expression> getChildNodes() {
        return combinedQualifications.getChildNodes();
    }

    @Override
    public Iterator<Expression> walk() {
        return null;
    }

    public void put(Qualification qualification){
        rawQualifications.add(qualification);
    }

    @Override
    public void fuzz() {
        assert !rawQualifications.isEmpty();
        List<Expression> merged = rawQualifications;
        while (merged.size() > 1){
            merged = randomlyMerge(merged);
        }
        assert merged.size() == 1;
        combinedQualifications = merged.get(0);
    }

    public boolean isEmpty(){
        return rawQualifications.isEmpty();
    }

    private List<Expression> randomlyMerge(List<Expression> qualifications){
        if(qualifications.size() < 2){
            return qualifications;
        }
        if(qualifications.size() == 2){
            return Collections.singletonList(combine(qualifications.get(0), qualifications.get(1)));
        }
        final List<Expression> result = new ArrayList<>();
        final List<Expression> template = ListUtil.copyListShuffle(qualifications);
        final int splitIndex = FuzzUtil.randomIntFromRange(1, template.size() - 1);
        final List<Expression> lhs = template.subList(0, splitIndex);
        final List<Expression> rhs = template.subList(splitIndex, template.size());
        result.addAll(randomlyMerge(lhs));
        result.addAll(randomlyMerge(rhs));
        return result;
    }

    private Expression combine(Expression left, Expression right){
        Qualification qualification = new Qualification(FuzzUtil.probability(50) ? StaticSymbol.AND:StaticSymbol.OR);
        final AtomicInteger counter = new AtomicInteger(0);
        java.util.function.Function<Expression, Expression> tryToNegative = (qual) -> {
            if(qual instanceof Qualification && FuzzUtil.probability(3 - counter.get())){
                counter.set(counter.get() + 1);
                return ((Qualification) qual).toNegative();
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
