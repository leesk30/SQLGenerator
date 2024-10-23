package org.lee.statement.expression.generator;

import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.statistic.RelatedStatistic;
import org.lee.statement.support.SQLStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class RelationalGenerator<T extends Expression>
        implements IExpressionGenerator<T> {
    protected final List<RangeTableReference> candidateRelations;

    protected final List<Pair<Scalar, Scalar>> relatedPair = new ArrayList<>();
    protected boolean isRelatedPairAlwaysEmpty = false;
    protected final RangeTableReference left;
    protected final RangeTableReference right;
    protected final RelatedStatistic statistic;
    protected final SQLStatement statement;

    protected RelationalGenerator(SQLStatement stmt, RangeTableReference lhs, RangeTableReference rhs){
        statement = stmt;
        left = lhs;
        right = rhs;
        statistic = new RelatedStatistic(left, right);
        candidateRelations = Collections.unmodifiableList(Arrays.asList(lhs, rhs));
    }

    protected List<RangeTableReference> getCandidate(){
        return candidateRelations;
    }

    protected void doCache(){
        if(isRelatedPairAlwaysEmpty || !relatedPair.isEmpty()){
            return;
        }
        final int size = candidateRelations.size();
        final int benchmarkIndex = Utility.randomIntFromRange(0, size);
        final RangeTableReference benchmark = candidateRelations.get(benchmarkIndex);
        candidateRelations.stream()
                        .filter(rtr -> rtr != benchmark)
                        .flatMap(rtr -> rtr.getFieldReferences().stream())
                        .forEach(
                                cur -> benchmark.getFieldReferences()
                                        .stream()
                                        .filter(b -> cur.getType().isSimilarWith(b.getType()))
                                        .forEach(b -> relatedPair.add(new Pair<>(cur, b)))
                        );
        isRelatedPairAlwaysEmpty = relatedPair.isEmpty();
    }

    protected Pair<Scalar, Scalar> getPair(){
        doCache();
        if(relatedPair.isEmpty())
            return null;
        final int index = Utility.randomIntFromRange(0, relatedPair.size());
        return relatedPair.remove(index);
    }

    @Override
    public SQLStatement retrieveStatement() {
        return statement;
    }

    @Override
    public List<Scalar> getWholeScopeCandidates() {
        final int capacity = left.getFieldReferences().size() + right.getFieldReferences().size();
        if(capacity == 0){
            return Collections.emptyList();
        }
        List<Scalar> candidates = new ArrayList<>(capacity);
        candidates.addAll(left.getFieldReferences());
        candidates.addAll(right.getFieldReferences());
        return candidates;
    }
}
