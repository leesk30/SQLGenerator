package org.lee.statement.clause;

import org.lee.common.SGException;
import org.lee.fuzzer.Generator;
import org.lee.fuzzer.expr.GeneralExpressionGenerator;
import org.lee.rules.RuleName;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.complex.TargetEntry;
import org.lee.node.NodeTag;
import org.lee.statement.expression.Expression;
import org.lee.statement.select.AbstractSimpleSelectStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;
import org.lee.type.TypeTag;
import org.lee.util.ListUtil;
import org.lee.util.FuzzUtil;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SelectClause extends Clause<TargetEntry> {
    public SelectClause(SelectStatement statement) {
        super(statement);
    }

    @Override
    public String getString() {
        return "SELECT " + this.nodeArrayToString(children);
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.selectClause;
    }

    @Override
    public void fuzz() {
        SelectStatement statement = (SelectStatement) this.statement;
        if(statement.getSelectType() == SelectType.clause){
            return;
        }
        Clause<RangeTableReference> fromClause = ((AbstractSimpleSelectStatement) statement).getFromClause();
        simpleFuzzProjections(fromClause.getChildNodes());
        return;
    }

    private void fuzzProjections(List<RangeTableReference> rangeTableReferences){
        // todo: combine choose
    }

    private void fuzzProjections(List<RangeTableReference> rangeTableReferences, List<TypeTag> typeLimitations){
        // todo: combine choose by limitations
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
        IntStream.range(0, projectionNums).sequential().forEach(
                i-> {
                    Expression expression = generator.generate();
                    TargetEntry entry = new TargetEntry(expression);
                    entry.setAlias();
                    children.add(entry);
                }
        );
    }
}
