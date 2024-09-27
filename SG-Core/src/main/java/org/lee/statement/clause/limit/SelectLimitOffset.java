package org.lee.statement.clause.limit;


import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.statement.select.SelectStatement;
import org.lee.common.util.FuzzUtil;

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
                limitNode.set(FuzzUtil.randomIntFromRange(1, 100));
            }
        }

        if(config.probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(FuzzUtil.randomIntFromRange(1, 100));
        }
    }
}
