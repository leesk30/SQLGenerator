package org.lee.statement.clause.condition;

import org.lee.common.DevTempConf;
import org.lee.entry.FieldReference;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.literal.LiteralInt;
import org.lee.entry.scalar.NameProxy;
import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
import org.lee.common.config.RuleName;
import org.lee.statement.clause.Clause;
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
        return "GROUP BY " + nodeArrayToString(children);
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
        targetEntries.stream().parallel().map(TargetEntry::getWrapped).forEach(
                scalar -> {
                    if(scalar instanceof Expression){
                        Expression expression = (Expression) scalar;
                        Pair<List<FieldReference>, List<FieldReference>> extracted = expression.extractAggregate();
                        extracted.getFirst().orElse(Collections.emptyList()).forEach(
                                fieldReference -> inAggregate.put(fieldReference.getString(), fieldReference)
                        );
                        extracted.getSecond().orElse(Collections.emptyList()).forEach(
                                fieldReference -> nonAggregate.put(fieldReference.getString(), fieldReference)
                        );
                    }else if(scalar instanceof NameProxy || scalar instanceof FieldReference) {
                        nonAggregate.put(scalar.getString(), scalar);
                    }else {
                        throw new RuntimeException("The target entry only should be proxy or reference. Not a " + scalar.getClass().getName());
                    }
                }
        );

        Set<String> intersected = inAggregate.keySet();
        intersected.removeAll(nonAggregate.keySet());

        if(nonAggregate.isEmpty() && intersected.isEmpty()){
            return;
        }
        children.addAll(nonAggregate.values());
        intersected.stream().parallel().filter(s -> FuzzUtil.probability(25)).forEach(key -> children.add(inAggregate.get(key)));
        Collections.shuffle(children);

    }


    @Override
    public void fuzz() {
        final boolean isAggregateRequiredGroupBy = statement.confirm(RuleName.AGGREGATION_REQUIRED_GROUP_BY);
        if(!isAggregateRequiredGroupBy && !FuzzUtil.probability(DevTempConf.GROUP_BY_CLAUSE_FUZZ_PROBABILITY)){
            return;
        }
        final List<TargetEntry> targetEntries = ((SelectStatement)statement).project();
        if(isAggregateRequiredGroupBy){
            groupByNonAgg(targetEntries);
            return;
        }
        groupByAll(targetEntries.size());

    }
}
