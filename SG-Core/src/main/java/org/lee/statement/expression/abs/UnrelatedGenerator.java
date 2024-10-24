package org.lee.statement.expression.abs;

import org.lee.common.Utility;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.statistic.RelatedStatistic;
import org.lee.statement.expression.statistic.UnrelatedStatistic;
import org.lee.statement.support.SQLStatement;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class UnrelatedGenerator<T extends Expression>
        extends AbstractExpressionGenerator<T> {
    protected final List<Scalar> rawScalarCandidates;
    protected final List<Scalar> replicated;
    protected final UnrelatedStatistic statistic;

    protected UnrelatedGenerator(SQLStatement statement, Scalar ... scalars){
        super(statement);
        rawScalarCandidates = Arrays.asList(scalars);
        replicated = Utility.copyFrozenList(rawScalarCandidates);
        statistic = new UnrelatedStatistic(rawScalarCandidates);
    }

    protected UnrelatedGenerator(SQLStatement statement, List<? extends Scalar> expresssionList){
        super(statement);
        rawScalarCandidates = new ArrayList<>(expresssionList.size());
        rawScalarCandidates.addAll(expresssionList);
        replicated = Utility.copyFrozenList(rawScalarCandidates);
        statistic = new UnrelatedStatistic(replicated);
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

    @Override
    public UnrelatedStatistic getStatistic() {
        return statistic;
    }
    abstract public T generate(TypeTag require);

}
