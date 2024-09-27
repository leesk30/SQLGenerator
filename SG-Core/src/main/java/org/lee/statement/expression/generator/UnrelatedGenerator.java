package org.lee.statement.expression.generator;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.scalar.Scalar;
import org.lee.base.Generator;
import org.lee.statement.expression.Expression;
import org.lee.statement.support.SupportRuntimeConfiguration;
import org.lee.symbol.Finder;
import org.lee.type.TypeTag;
import org.lee.common.util.FuzzUtil;
import org.lee.common.util.ListUtil;
import org.lee.statement.expression.statistic.UnrelatedStatistic;
import java.util.*;
import java.util.stream.IntStream;

public abstract class UnrelatedGenerator<T> implements Generator<T>, SupportRuntimeConfiguration {
    protected final List<Expression> candidateList;
    protected final List<Expression> replicated;
    protected final UnrelatedStatistic statistic;
    protected final RuntimeConfiguration config;
    protected final Finder finder = Finder.getFinder();

    protected UnrelatedGenerator(RuntimeConfiguration conf, Scalar ... scalars){
        config = conf;
        candidateList = new ArrayList<>(scalars.length);
        IntStream.range(0, scalars.length).sequential().forEach(i -> candidateList.add(scalars[i].toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
        statistic = new UnrelatedStatistic(replicated);
    }

    protected UnrelatedGenerator(RuntimeConfiguration conf, List<? extends Scalar> expresssionList){
        config = conf;
        candidateList = new ArrayList<>(expresssionList.size());
        IntStream.range(0, expresssionList.size()).sequential().forEach(
                i -> candidateList.add(expresssionList.get(i).toExpression()));
        replicated = ListUtil.copyFrozenList(candidateList);
        statistic = new UnrelatedStatistic(replicated);
    }

    @Override
    public RuntimeConfiguration getConfig() {
        return config;
    }

    @Override
    public T generate(){
        return generate(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }
    abstract T generate(TypeTag require);

}
