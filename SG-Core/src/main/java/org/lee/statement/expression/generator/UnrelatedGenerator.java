package org.lee.statement.expression.generator;

import org.lee.SQLGeneratorContext;
import org.lee.common.Utility;
import org.lee.common.global.Finder;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.statistic.UnrelatedStatistic;
import org.lee.statement.support.SQLStatement;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class UnrelatedGenerator<T extends Expression>
        implements IExpressionGenerator<T> {
    protected final List<Scalar> rawScalarCandidates;
    protected final List<Scalar> replicated;
    protected final UnrelatedStatistic statistic;
    protected final Finder finder = SQLGeneratorContext.getCurrentFinder();
    protected final SQLStatement statement;

    protected UnrelatedGenerator(SQLStatement statement, Scalar ... scalars){
        rawScalarCandidates = Arrays.asList(scalars);
        replicated = Utility.copyFrozenList(rawScalarCandidates);
        statistic = new UnrelatedStatistic(rawScalarCandidates);
        this.statement = statement;
    }

    protected UnrelatedGenerator(SQLStatement statement, List<? extends Scalar> expresssionList){
        rawScalarCandidates = new ArrayList<>(expresssionList.size());
        rawScalarCandidates.addAll(expresssionList);
        replicated = Utility.copyFrozenList(rawScalarCandidates);
        statistic = new UnrelatedStatistic(replicated);
        this.statement = statement;
    }

    public SQLStatement retrieveStatement() {
        return statement;
    }

    @Override
    public List<Scalar> getWholeScopeCandidates() {
        List<Scalar> candidates = new ArrayList<>(rawScalarCandidates.size());
        candidates.addAll(rawScalarCandidates);
        return candidates;
    }

    @Override
    public T generate(){
        return generate(Utility.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }
    abstract T generate(TypeTag require);

}
