package org.lee.statement.clause.project;

import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.exception.NotImplementedException;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.statement.clause.Clause;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.generator.GeneralExpressionGenerator;
import org.lee.statement.select.AbstractSimpleSelectStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectClauseWithinFrom extends SelectClause{
    public SelectClauseWithinFrom(SelectStatement statement) {
        super(statement);
    }

    @Override
    public void fuzz() {
        SelectStatement statement = (SelectStatement) this.statement;
        if(statement.getSelectType() == SelectType.clause){
            return;
        }
//        Clause<RangeTableReference> fromClause = ((AbstractSimpleSelectStatement) statement).getFromClause();
//        simpleFuzzProjections(fromClause.getChildNodes());
        if(statement.getProjectTypeLimitation().isEmpty()){
            nonLimitationsProjectionFuzz();
        }else {
            withLimitationsProjectionFuzz();
        }
    }

    @Override
    public AbstractSimpleSelectStatement statement(){
        return (AbstractSimpleSelectStatement) statement;
    }

    private GeneralExpressionGenerator getProjectionGenerator(){
        final Clause<RangeTableReference> fromClause = statement().getFromClause();
        final List<FieldReference> fieldReferences = new ArrayList<>();
        // From each rangeTableReference, we extract their fieldReferences as candidates for projection.
        fromClause.getChildNodes().forEach(ref -> fieldReferences.addAll(ref.getFieldReferences()));
        final boolean enableAggregation = confirm(Rule.REQUIRE_SCALA) && confirm(Rule.SCALAR_FORCE_USING_AGGREGATION);
        if(enableAggregation){
            // do nothing
            logger.debug(
                    "The statement requires a scalar which should be wrapped with an aggregate. " +
                            "To stop retrieving aggregation, we stop the aggregator search here."
            );
        }
        return new GeneralExpressionGenerator(enableAggregation, this.statement, fieldReferences);
    }

    private void nonLimitationsProjectionFuzz(){
        final GeneralExpressionGenerator generator = getProjectionGenerator();
        final int numOfEntry = statement().getFromClause().size();
        final int numOfProjection = Utility.randomIntFromRange(numOfEntry, numOfEntry*2);
        for (int i = 0; i < numOfProjection; i++) {
            if(config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY) ||
                    config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY)){
                processEntry(generator.generate());
            }else {
                processEntry(generator.fallback());
            }
        }
    }

    private void  withLimitationsProjectionFuzz(){
        GeneralExpressionGenerator generator = getProjectionGenerator();
        statement().getProjectTypeLimitation().forEach(
                requiredType -> {
                    if(config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY) || config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY)){
                        processEntry(generator.generate(requiredType));
                    }else {
                        processEntry(generator.fallback(requiredType));
                    }
                }
        );
    }

    private void simpleFuzzProjections(List<RangeTableReference> rangeTableReferences){
        final List<RangeTableReference> shuffledReferences = Utility.copyListShuffle(rangeTableReferences);
        final int[] numOfEachCandidate = shuffledReferences.stream().mapToInt(reference -> reference.getFieldReferences().size()).toArray();
        final int numOfCandidate = Arrays.stream(numOfEachCandidate).sum();
        final int mayChooseNum = Utility.randomIntFromRange(1, numOfCandidate + 1);
        final int averageChooseRoundNum = Math.max((numOfCandidate / numOfEachCandidate.length), 1);
        final boolean enableDuplicateProjections = statement.confirm(Rule.ENABLE_DUPLICATE_FILED_PROJECTIONS);
//        final int[] mutableFactor = {mayChooseNum};
        final List<FieldReference> fieldReferences = new ArrayList<>(mayChooseNum);

        for(int i=0; i<numOfEachCandidate.length; i++){
            final List<FieldReference> shuffledCandidates = Utility.copyListShuffle(shuffledReferences.get(i).getFieldReferences());
            assert !shuffledCandidates.isEmpty();
            if(enableDuplicateProjections){
                final int fromThisChooseNum = Math.min(shuffledCandidates.size(), averageChooseRoundNum);
                for(int j=0; j<fromThisChooseNum; j++){
                    fieldReferences.add(Utility.randomlyChooseFrom(shuffledCandidates));
                }
            }else {
                throw new NotImplementedException("Not implement for disable duplicate projections");
            }
        }

        Generator<Expression> generator = new GeneralExpressionGenerator(true, this.statement, fieldReferences);
        final int max = Math.max(numOfEachCandidate.length, averageChooseRoundNum / 2);
        final int projectionNums = Utility.randomIntFromRange(numOfEachCandidate.length, max);
        for (int i = 0; i < projectionNums; i++) {
            processEntry(generator.generate());
        }
    }
}
