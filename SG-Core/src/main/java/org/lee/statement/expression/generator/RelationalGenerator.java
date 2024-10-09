package org.lee.statement.expression.generator;

import org.lee.common.Utility;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Scalar;
import org.lee.base.Generator;
import org.lee.statement.SQLStatement;
import org.lee.statement.expression.statistic.RelatedStatistic;
import org.lee.statement.expression.Expression;
import org.lee.statement.support.Logging;
import org.lee.statement.support.SupportRuntimeConfiguration;
import org.lee.common.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

public abstract class RelationalGenerator<T extends Expression>
        implements Generator<T>, SupportRuntimeConfiguration, Logging {
    protected final List<RangeTableReference> candidateRelations;

    protected List<Pair<Scalar, Scalar>> relatedPair = null;
    protected final RangeTableReference left;
    protected final RangeTableReference right;
    protected final RelatedStatistic statistic;
    protected final RuntimeConfiguration config;
    protected final SQLStatement statement;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected RelationalGenerator(SQLStatement stmt, RangeTableReference lhs, RangeTableReference rhs){
        statement = stmt;
        config = statement.getConfig();
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

        relatedPair = new ArrayList<>();
        final int size = candidateRelations.size();
        final int benchmarkIndex = Utility.randomIntFromRange(0, size);
        final RangeTableReference benchmark = candidateRelations.get(benchmarkIndex);
        IntStream.range(0, size - 1).sequential()
                .map(i -> i >= benchmarkIndex ? i + 1 : i)
                .forEach(
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
            final int index = Utility.randomIntFromRange(0, safetyRef.size());
            return safetyRef.remove(index);
        }
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
}
