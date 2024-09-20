package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Expression;
import org.lee.symbol.Finder;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;
import org.lee.fuzzer.expr.statistic.UnrelatedStatistic;
import java.util.*;
import java.util.stream.IntStream;

public abstract class UnrelatedGenerator<T> implements Generator<T> {
    protected final List<Expression> candidateList;
    protected final List<Expression> replicated;
    protected final UnrelatedStatistic statistic;
    protected final Finder finder = Finder.getFinder();

    protected UnrelatedGenerator(Scalar ... scalars){
        candidateList = new Vector<>(scalars.length);
        IntStream.range(0, scalars.length).parallel().forEach(i -> candidateList.add(scalars[i].toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
        statistic = new UnrelatedStatistic(replicated);
    }

    protected UnrelatedGenerator(List<? extends Scalar> expresssionList){
        candidateList = new Vector<>(expresssionList.size());
        IntStream.range(0, expresssionList.size()).parallel().forEach(
                i -> candidateList.add(expresssionList.get(i).toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
        statistic = new UnrelatedStatistic(replicated);
    }

    @Override
    public T generate(){
        return generate(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }
    abstract T generate(TypeTag require);

}
