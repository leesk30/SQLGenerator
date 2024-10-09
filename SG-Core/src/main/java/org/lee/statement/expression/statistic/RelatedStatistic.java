package org.lee.statement.expression.statistic;

import org.lee.common.Pair;
import org.lee.entry.RangeTableReference;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class RelatedStatistic implements GeneratorStatistic{
    private final Pair<RangeTableReference, RangeTableReference> relatedEntryPair;
    private final Set<String> keys = new HashSet<>();
    private final Map<String, UnrelatedStatistic> statisticMap = new ConcurrentHashMap<>();
    private final Map<String, Set<TypeTag>> summaryMap = new ConcurrentHashMap<>();

    public RelatedStatistic(RangeTableReference left, RangeTableReference right){
        relatedEntryPair = new Pair<>(left, right);
        Stream.of(left, right).forEach(
                one -> {
                    final String uniqueKey = one.getUniqueName().toString();
                    final UnrelatedStatistic statistic = new UnrelatedStatistic(one.getFieldReferences());
                    if(keys.contains(uniqueKey)){
                        throw new RuntimeException("Duplicate attribute name in statistic.");
                    }
                    keys.add(uniqueKey);
                    statisticMap.put(uniqueKey, statistic);
                    summaryMap.put(uniqueKey, statistic.getGroupedType().keySet());
                }
        );
        collect();
    }

    private void collect(){
        Set<TypeTag> commonTypes;
        Set<TypeCategory> commonCategories;
        Set<TypeTag> left = summaryMap.get(relatedEntryPair.getFirst().toString());
        Set<TypeTag> right = summaryMap.get(relatedEntryPair.getSecond().toString());
        // todo
    }
}
