package org.lee.statement.expression.generator;

import org.lee.base.Generator;
import org.lee.common.structure.Pair;
import org.lee.common.Utility;
import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.statistic.RelatedStatistic;
import org.lee.statement.support.Logging;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.SupportRuntimeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class RelationalGenerator<T extends Expression>
        implements Generator<T>, SupportRuntimeConfiguration, Logging {
    protected final List<RangeTableReference> candidateRelations;

    protected final List<Pair<Scalar, Scalar>> relatedPair = new ArrayList<>();
    protected boolean isRelatedPairAlwaysEmpty = false;
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
