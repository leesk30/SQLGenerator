package org.lee.statement.clause;

import org.lee.common.DevTempConf;
import org.lee.entry.FieldReference;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.literal.LiteralInt;
import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
import org.lee.rules.RuleName;
import org.lee.statement.expression.Expression;
import org.lee.statement.select.SelectStatement;
import org.lee.util.FuzzUtil;
import org.lee.util.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class GroupByClause extends Clause<Scalar> {
    public GroupByClause(SelectStatement statement) {
        super(statement);
    }

    public GroupByClause(SelectStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.groupByClause;
    }


    private void groupByAll(int size){
        IntStream.range(0, size).parallel().forEach(
                i -> {
                    Scalar scalar = new LiteralInt(i + 1);
                    children.add(scalar);
                }
        );
        Collections.shuffle(children);
    }

    private void groupByNonAgg(final List<TargetEntry> targetEntries){
        Map<String, Scalar> inAggregate = new ConcurrentHashMap<>(targetEntries.size());
        Map<String, Scalar> nonAggregate = new ConcurrentHashMap<>(targetEntries.size() * 2);
        targetEntries.stream().parallel().map(TargetEntry::getTarget).forEach(
                scalar -> {
                    if(scalar instanceof Expression){
                        Expression expression = (Expression) scalar;
                        Pair<List<FieldReference>, List<FieldReference>> extracted = expression.extractAggregate();
                        extracted.getFirst().orElse(Collections.emptyList()).forEach(fieldReference -> inAggregate.put(fieldReference.getString(), fieldReference));
                        extracted.getSecond().orElse(Collections.emptyList()).forEach(fieldReference -> nonAggregate.put(fieldReference.getString(), fieldReference));
                    }else {
                        nonAggregate.put(scalar.getString(), scalar);
                    }
                }
        );

        Set<String> intersected = inAggregate.keySet();
        intersected.removeAll(nonAggregate.keySet());

        if(nonAggregate.isEmpty() && intersected.isEmpty()){
            return;
        }
        List<String> keys = new Vector<>(intersected);
        Collections.shuffle(keys);
        // todo: choose random
//        IntStream.range(0, FuzzUtil.randomIntFromRange(0, intersected.size())).parallel().forEach(
//                keys
//        );

    }


    @Override
    public void fuzz() {
        final boolean isAggregateRequiredGroupBy = statement.getRuleSet().confirm(RuleName.AGGREGATION_REQUIRED_GROUP_BY);
        if(!isAggregateRequiredGroupBy && !FuzzUtil.probability(DevTempConf.GROUP_BY_CLAUSE_FUZZ_PROBABILITY)){
            return;
        }
        final List<TargetEntry> targetEntries = ((SelectStatement)statement).project();

        if(!isAggregateRequiredGroupBy){
            groupByAll(targetEntries.size());
            return;
        }
        groupByNonAgg(targetEntries);

    }
}
