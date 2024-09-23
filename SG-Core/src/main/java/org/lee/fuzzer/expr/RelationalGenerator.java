package org.lee.fuzzer.expr;

import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.literal.Literal;
import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.fuzzer.expr.statistic.RelatedStatistic;
import org.lee.statement.expression.Expression;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.Pair;

import java.util.*;
import java.util.stream.IntStream;

public abstract class RelationalGenerator<T extends Expression> implements Generator<T> {
    protected final List<RangeTableReference> candidateRelations;

    protected List<Pair<Scalar, Scalar>> relatedPair = null;
    protected final RangeTableReference left;
    protected final RangeTableReference right;
    protected final RelatedStatistic statistic;

    protected RelationalGenerator(RangeTableReference lhs, RangeTableReference rhs){
        left = lhs;
        right = rhs;
        statistic = new RelatedStatistic(left, right);
        candidateRelations = Collections.unmodifiableList(Arrays.asList(lhs, rhs));
    }

    protected List<RangeTableReference> getCandidate(){
        return candidateRelations;
    }

    protected synchronized void doCache(){
        if(relatedPair != null){
            return;
        }

        relatedPair = new Vector<>();
        final int size = candidateRelations.size();
        final int benchmarkIndex = FuzzUtil.randomIntFromRange(0, size);
        final RangeTableReference benchmark = candidateRelations.get(benchmarkIndex);
        IntStream.range(0, size - 1).map(i -> i >= benchmarkIndex ? i + 1 : i).forEach(
                i -> candidateRelations
                        .get(i)
                        .getFieldReferences()
                        .forEach(
                                cur ->
                                        benchmark.getFieldReferences()
                                                .stream()
                                                .filter(benchRef -> cur.getType().isSimilarWith(benchRef.getType()))
                                                .forEach(benchRef -> relatedPair.add(new Pair<>(cur, benchRef)))
                        )
        );
    }

    protected Pair<Scalar, Scalar> consumPair(){
        doCache();
        final List<Pair<Scalar, Scalar>> safetyRef = relatedPair;
        assert safetyRef != null;
        synchronized (safetyRef){
            if(safetyRef.isEmpty())
                return null;
            final int index = FuzzUtil.randomIntFromRange(0, safetyRef.size());
            return safetyRef.remove(index);
        }
    }

}
