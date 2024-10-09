package org.lee.statement.clause.project;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.common.exception.NotImplementedException;
import org.lee.base.Generator;
import org.lee.statement.expression.generator.GeneralExpressionGenerator;
import org.lee.common.config.Rule;
import org.lee.statement.clause.Clause;
import org.lee.statement.expression.Expression;
import org.lee.statement.select.AbstractSimpleSelectStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

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
        Clause<RangeTableReference> fromClause = statement().getFromClause();
        List<FieldReference> fieldReferences = new ArrayList<>();
        fromClause.getChildNodes().forEach(ref -> fieldReferences.addAll(ref.getFieldReferences()));
        return new GeneralExpressionGenerator(this.statement, fieldReferences);
    }

    private void nonLimitationsProjectionFuzz(){
        GeneralExpressionGenerator generator = getProjectionGenerator();
        final int numOfEntry = statement().getFromClause().size();
        final int numOfProjection = Utility.randomIntFromRange(numOfEntry, numOfEntry*2);

        IntStream.range(0, numOfProjection).sequential().forEach(
                i-> {
                    if(config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY) || config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY)){
                        processEntry(generator.generate());
                    }else {
                        processEntry(generator.fallback());
                    }
                }
        );
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

        IntStream.range(0, numOfEachCandidate.length).sequential().forEach(i -> {
            final List<FieldReference> shuffledCandidates = Utility.copyListShuffle(shuffledReferences.get(i).getFieldReferences());
            assert !shuffledCandidates.isEmpty();
            if(enableDuplicateProjections){
                final int fromThisChooseNum = Math.min(shuffledCandidates.size(), averageChooseRoundNum);
                IntStream.range(0, fromThisChooseNum).sequential().forEach(j -> {
                    FieldReference choose = Utility.randomlyChooseFrom(shuffledCandidates);
                    fieldReferences.add(choose);
                });
            }else {
                throw new NotImplementedException("Not implement for disable duplicate projections");
            }
        });

        Generator<Expression> generator = new GeneralExpressionGenerator(this.statement, fieldReferences);
        final int max = Math.max(numOfEachCandidate.length, averageChooseRoundNum / 2);
        final int projectionNums = Utility.randomIntFromRange(numOfEachCandidate.length, max);
        IntStream.range(0, projectionNums).sequential().forEach(i-> processEntry(generator.generate()));
    }
}
