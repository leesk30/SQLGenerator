package org.lee.statement.clause.from;

import org.lee.common.config.Conf;
import org.lee.entry.RangeTableReference;
import org.lee.statement.select.SelectStatement;
import org.lee.common.util.FuzzUtil;

import java.util.stream.IntStream;

public final class SelectFromClause extends FromClause {
    public SelectFromClause(SelectStatement statement){
        super(statement);
    }

    @Override
    public void fuzz() {
        final int rteJoinNumber = FuzzUtil.randomIntFromRange(1, config.getInt(Conf.MAX_FROM_CLAUSE_RTE_JOIN_NUM));
        final RangeTableReference[][] candidatesArray = new RangeTableReference[rteJoinNumber][];
        IntStream.range(0, rteJoinNumber).sequential().forEach(
                i -> {
                    final int rteJoinEntryNumber = FuzzUtil.randomIntFromRange(1, config.getInt(Conf.MAX_RTE_JOIN_ENTRY_NUM));
                    final RangeTableReference[] candidates = new RangeTableReference[rteJoinEntryNumber];
                    candidatesArray[i] = candidates;
                    IntStream.range(0, rteJoinEntryNumber).forEach(
                            j -> {
                                RangeTableReference reference = randomlyGetRangeReference();
                                reference.setAlias();
                                candidates[j] = reference;
                            }
                    );
                }
        );
        merge(candidatesArray);
    }
}
