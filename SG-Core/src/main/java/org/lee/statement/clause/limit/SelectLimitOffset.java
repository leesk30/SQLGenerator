package org.lee.statement.clause.limit;


import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.statement.select.SelectStatement;

public class SelectLimitOffset extends LimitOffset {

    public SelectLimitOffset(SelectStatement statement) {
        super(statement);
    }

    @Override
    public void fuzz() {
        if(config.probability(Conf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
            if(statement.confirm(Rule.REQUIRE_SCALA)){
                limitNode.set(1);
            }else {
                limitNode.set(Utility.randomIntFromRange(1, 100));
            }
        }

        if(config.probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(Utility.randomIntFromRange(1, 100));
        }
    }
}
