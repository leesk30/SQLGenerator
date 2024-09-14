package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Finder;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;

public abstract class UnrelatedGenerator<T> implements Generator<T> {
    protected final List<Expression> candidateList;
    protected final List<Expression> replicated;
    protected final Finder finder = Finder.getFinder();

    protected UnrelatedGenerator(Scalar ... scalars){
        candidateList = new Vector<>(scalars.length);
        IntStream.range(0, scalars.length).parallel().forEach(i -> candidateList.add(scalars[i].toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
    }

    protected UnrelatedGenerator(List<? extends Scalar> expresssionList){
        candidateList = new Vector<>(expresssionList.size());
        IntStream.range(0, expresssionList.size()).parallel().forEach(
                i -> candidateList.add(expresssionList.get(i).toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
    }

    @Override
    public T generate(){
        return generate(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }
    abstract T generate(TypeTag require);
}
