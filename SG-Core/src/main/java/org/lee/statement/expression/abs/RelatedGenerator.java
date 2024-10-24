package org.lee.statement.expression.abs;

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

public abstract class RelatedGenerator<T extends Expression>
        extends AbstractExpressionGenerator<T> {

    protected final List<Pair<Scalar, Scalar>> relatedPair = new ArrayList<>();
    protected final RangeTableReference left;
    protected final RangeTableReference right;
    protected final RelatedStatistic statistic;

    protected RelatedGenerator(SQLStatement stmt, RangeTableReference lhs, RangeTableReference rhs){
        super(stmt);
        left = lhs;
        right = rhs;
        statistic = new RelatedStatistic(left.getFieldReferences(), right.getFieldReferences());
    }

    public Pair<Scalar, Scalar> getPair(){
        if(probability(50)){
            return statistic.getRelatedCategoryPair();
        }
        return statistic.getRelatedTypePair();
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

    @Override
    public RelatedStatistic getStatistic() {
        return statistic;
    }
}
