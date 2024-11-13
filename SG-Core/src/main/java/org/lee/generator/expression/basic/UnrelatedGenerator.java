package org.lee.generator.expression.basic;

import org.lee.common.Utility;
import org.lee.generator.expression.statistic.UnrelatedStatistic;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Expression;
import org.lee.sql.statement.SQLStatement;
import org.lee.sql.type.TypeTag;

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
        statistic = new UnrelatedStatistic(this, rawScalarCandidates);
    }

    protected UnrelatedGenerator(SQLStatement statement, List<? extends Scalar> expresssionList){
        super(statement);
        rawScalarCandidates = new ArrayList<>(expresssionList.size());
        rawScalarCandidates.addAll(expresssionList);
        replicated = Utility.copyFrozenList(rawScalarCandidates);
        statistic = new UnrelatedStatistic(this, replicated);
    }

    protected UnrelatedGenerator(SQLStatement statement, UnrelatedStatistic statistic){
        super(statement);
        rawScalarCandidates = new ArrayList<>(statistic.getWholeScopeCandidates());
        rawScalarCandidates.addAll(statistic.getWholeScopeCandidates());
        replicated = Utility.copyFrozenList(rawScalarCandidates);
        this.statistic = statistic;
    }

    @Override
    public UnrelatedStatistic getStatistic() {
        return statistic;
    }
    abstract public T generate(TypeTag require);

}
