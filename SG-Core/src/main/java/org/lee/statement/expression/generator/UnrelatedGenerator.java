package org.lee.statement.expression.generator;

import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.SQLStatement;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.statistic.UnrelatedStatistic;
import org.lee.statement.support.Logging;
import org.lee.statement.support.SupportRuntimeConfiguration;
import org.lee.symbol.Finder;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public abstract class UnrelatedGenerator<T> implements
        Generator<T>, SupportRuntimeConfiguration, Logging {
    protected final List<Expression> candidateList;
    protected final List<Expression> replicated;
    protected final UnrelatedStatistic statistic;
    protected final RuntimeConfiguration config;
    protected final Finder finder = Finder.getFinder();
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final SQLStatement statement;

    protected UnrelatedGenerator(SQLStatement statement, Scalar ... scalars){
        config = statement.getConfig();
        candidateList = new ArrayList<>(scalars.length);
        IntStream.range(0, scalars.length).sequential().forEach(i -> candidateList.add(scalars[i].toExpression()));
        replicated = Utility.copyFrozenList(candidateList);
        statistic = new UnrelatedStatistic(replicated);
        this.statement = statement;
    }

    protected UnrelatedGenerator(SQLStatement statement, List<? extends Scalar> expresssionList){
        config = statement.getConfig();
        candidateList = new ArrayList<>(expresssionList.size());
        IntStream.range(0, expresssionList.size()).sequential().forEach(
                i -> candidateList.add(expresssionList.get(i).toExpression()));
        replicated = Utility.copyFrozenList(candidateList);
        statistic = new UnrelatedStatistic(replicated);
        this.statement = statement;
    }

    @Override
    public RuntimeConfiguration getConfig() {
        return config;
    }

    public SQLStatement getStatement() {
        return statement;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public T generate(){
        return generate(Utility.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }
    abstract T generate(TypeTag require);

}
