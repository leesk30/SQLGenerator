package org.lee.statement.clause.project;

import org.lee.base.NodeTag;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.common.structure.Pair;
import org.lee.entry.FieldReference;
import org.lee.entry.complex.TargetEntry;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.clause.Clause;
import org.lee.statement.select.SelectStatement;
import org.lee.type.literal.LiteralInt;

import java.util.*;

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
        Map<String, Scalar> inAggregate = new HashMap<>(targetEntries.size());
        Map<String, Scalar> nonAggregate = new HashMap<>(targetEntries.size() * 2);
        targetEntries.forEach(
                entry -> {
                    if(entry.isTargetEntryExpression()){
                        Pair<List<FieldReference>, List<FieldReference>> extracted = entry.getCachedExtracted();
                        extracted.getFirst().forEach(
                                fieldReference -> inAggregate.put(fieldReference.getString(), fieldReference)
                        );
                        extracted.getSecond().forEach(
                                fieldReference -> nonAggregate.put(fieldReference.getString(), fieldReference)
                        );
                    }else {
                        nonAggregate.put(entry.getWrapped().getString(), entry.getWrapped());
                    }
                }
        );

        Set<String> difference = Utility.diff(inAggregate.keySet(), nonAggregate.keySet());

        if(nonAggregate.isEmpty() && difference.isEmpty()){
            return;
        }
        children.addAll(nonAggregate.values());
        difference.stream().filter(s -> Utility.probability(25)).forEach(key -> children.add(inAggregate.get(key)));
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