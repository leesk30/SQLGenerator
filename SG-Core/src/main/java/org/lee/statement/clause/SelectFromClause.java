package org.lee.statement.clause;

import org.lee.common.DevTempConf;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.RangeTableReference;
import org.lee.statement.select.SelectStatement;
import org.lee.util.FuzzUtil;

import java.util.stream.IntStream;

public final class SelectFromClause extends FromClause{
    public SelectFromClause(SelectStatement statement){
        super(statement);
    }

    @Override
    public void fuzz() {
        final int rteJoinNumber = FuzzUtil.randomIntFromRange(1, DevTempConf.MAX_FROM_CLAUSE_RTE_JOIN_NUM);
        final RangeTableReference[][] candidatesArray = new RangeTableReference[rteJoinNumber][];
        IntStream.range(0, rteJoinNumber).parallel().forEach(
                i -> {
                    final int rteJoinEntryNumber = FuzzUtil.randomIntFromRange(1, DevTempConf.MAX_RTE_JOIN_ENTRY_NUM);
                    final RangeTableReference[] candidates = new RangeTableReference[rteJoinEntryNumber];
                    candidatesArray[i] = candidates;
                    IntStream.range(0, rteJoinEntryNumber).parallel().forEach(
                            j -> {
                                RangeTableReference reference = randomlyGetRangeReference();
                                reference.setAlias();
                                candidates[j] = reference;
                            }
                    );
                    candidatesArray[i] = candidates;
                }
        );
        merge(candidatesArray);
    }
}
