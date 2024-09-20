package org.lee.statement.clause.project;

import org.lee.common.DevTempConf;
import org.lee.common.SGException;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.fuzzer.expr.GeneralExpressionGenerator;
import org.lee.rules.ConstRule;
import org.lee.rules.RuleName;
import org.lee.statement.clause.Clause;
import org.lee.statement.clause.from.FromClause;
import org.lee.statement.expression.Expression;
import org.lee.statement.select.AbstractSimpleSelectStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.ListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
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
        List<FieldReference> fieldReferences = new Vector<>();
        fromClause.getChildNodes().stream().parallel().forEach(ref -> fieldReferences.addAll(ref.getFieldReferences()));
        return new GeneralExpressionGenerator(fieldReferences);
    }

    private void nonLimitationsProjectionFuzz(){
        GeneralExpressionGenerator generator = getProjectionGenerator();
        final int numOfEntry = statement().getFromClause().getRawEntryList().size();
        final int numOfProjection = FuzzUtil.randomIntFromRange(numOfEntry, numOfEntry*2);

        IntStream.range(0, numOfProjection).parallel().forEach(
                i-> {
                    if(FuzzUtil.probability(DevTempConf.EXPRESSION_RECURSION_PROBABILITY) || FuzzUtil.probability(DevTempConf.EXPRESSION_RECURSION_PROBABILITY)){
                        processEntry(generator.generate());
                    }else {
                        processEntry(generator.fallback());
                    }
                }
        );
    }

    private void  withLimitationsProjectionFuzz(){
        GeneralExpressionGenerator generator = getProjectionGenerator();
        statement().getProjectTypeLimitation().stream().parallel().forEachOrdered(
                requiredType -> {
                    if(FuzzUtil.probability(DevTempConf.EXPRESSION_RECURSION_PROBABILITY) || FuzzUtil.probability(DevTempConf.EXPRESSION_RECURSION_PROBABILITY)){
                        processEntry(generator.generate(requiredType));
                    }else {
                        processEntry(generator.fallback(requiredType));
                    }
                }
        );
    }

    private void simpleFuzzProjections(List<RangeTableReference> rangeTableReferences){
        final List<RangeTableReference> shuffledReferences = ListUtil.copyListShuffle(rangeTableReferences);
        final int[] numOfEachCandidate = shuffledReferences.stream().mapToInt(reference -> reference.getFieldReferences().size()).toArray();
        final int numOfCandidate = Arrays.stream(numOfEachCandidate).sum();
        final int mayChooseNum = FuzzUtil.randomIntFromRange(1, numOfCandidate + 1);
        final int averageChooseRoundNum = Math.max((numOfCandidate / numOfEachCandidate.length), 1);
        final boolean enableDuplicateProjections = statement.confirmByRuleName(RuleName.ENABLE_DUPLICATE_FILED_PROJECTIONS);
//        final int[] mutableFactor = {mayChooseNum};
        final List<FieldReference> fieldReferences = new Vector<>(mayChooseNum);

        IntStream.range(0, numOfEachCandidate.length).parallel().forEach(i -> {
            final List<FieldReference> shuffledCandidates = ListUtil.copyListShuffle(shuffledReferences.get(i).getFieldReferences());
            assert !shuffledCandidates.isEmpty();
            if(enableDuplicateProjections){
                final int fromThisChooseNum = Math.min(shuffledCandidates.size(), averageChooseRoundNum);
                IntStream.range(0, fromThisChooseNum).forEach(j -> {
                    FieldReference choose = FuzzUtil.randomlyChooseFrom(shuffledCandidates);
                    fieldReferences.add(choose);
                });
            }else {
                throw new SGException.NotImplementedException("Not implement for disable duplicate projections");
            }
        });

        Generator<Expression> generator = new GeneralExpressionGenerator(fieldReferences);
        final int max = Math.max(numOfEachCandidate.length, averageChooseRoundNum / 2);
        final int projectionNums = FuzzUtil.randomIntFromRange(numOfEachCandidate.length, max);
        IntStream.range(0, projectionNums).parallel().forEach(
                i-> {
                    Expression expression = generator.generate();
                    processEntry(expression);
                }
        );
    }
}
