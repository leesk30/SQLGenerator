package org.lee.sql.clause.from;

import org.lee.common.enumeration.Conf;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.statement.select.SelectStatement;

public final class SelectFromClause extends FromClause {
    public SelectFromClause(SelectStatement statement){
        super(statement);
    }

    @Override
    public void fuzz() {
        final int rteJoinNumber = RandomUtils.randomIntFromRange(1, config.getInt(Conf.MAX_FROM_CLAUSE_RTE_JOIN_NUM));
        final RangeTableReference[][] candidatesArray = new RangeTableReference[rteJoinNumber][];
        for (int i = 0; i < rteJoinNumber; i++) {
            final int rteJoinEntryNumber = RandomUtils.randomIntFromRange(1, config.getInt(Conf.MAX_RTE_JOIN_ENTRY_NUM));
            final RangeTableReference[] candidates = new RangeTableReference[rteJoinEntryNumber];
            candidatesArray[i] = candidates;
            for(int j = 0; j < candidatesArray[i].length; j++){
                RangeTableReference reference = randomlyGetRangeReference();
                reference.setAlias();
                candidates[j] = reference;
            }
        }
        merge(candidatesArray);
    }
}
