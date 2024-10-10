package org.lee.statement.clause.condition;

import org.lee.base.NodeTag;
import org.lee.common.Pair;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.entry.FieldReference;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.scalar.NameProxy;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.clause.Clause;
import org.lee.statement.expression.Expression;
import org.lee.statement.select.SelectStatement;
import org.lee.type.literal.LiteralInt;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
        for(int i = 0; i < size; i++){
            children.add(new LiteralInt(i + 1));
        }
        Collections.shuffle(children);
    }

    private void groupByNonAgg(final List<TargetEntry> targetEntries){
        Map<String, Scalar> inAggregate = new ConcurrentHashMap<>(targetEntries.size());
        Map<String, Scalar> nonAggregate = new ConcurrentHashMap<>(targetEntries.size() * 2);
        targetEntries.stream().map(TargetEntry::getWrapped).forEach(
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
        intersected.stream().filter(s -> Utility.probability(25)).forEach(key -> children.add(inAggregate.get(key)));
        Collections.shuffle(children);

    }


    @Override
    public void fuzz() {
        final boolean isAggregateRequiredGroupBy = statement.confirm(Rule.AGGREGATION_REQUIRED_GROUP_BY);
        if(!isAggregateRequiredGroupBy && !probability(Conf.GROUP_BY_CLAUSE_FUZZ_PROBABILITY)){
//            logger.debug("The statement is non-aggregation or the probability is not passed.");
            return;
        }
        final List<TargetEntry> targetEntries = ((SelectStatement)statement).project();
        if(isAggregateRequiredGroupBy){
//            logger.debug("The group by clause will be required by aggregation.");
            groupByNonAgg(targetEntries);
            return;
        }
//        logger.debug("The group by clause will group by all entries.");
        groupByAll(targetEntries.size());

    }
}
