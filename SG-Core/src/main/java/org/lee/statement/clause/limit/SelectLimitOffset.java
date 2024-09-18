package org.lee.statement.clause.limit;

import org.lee.common.DevTempConf;
import org.lee.rules.RuleName;
import org.lee.statement.select.SelectStatement;
import org.lee.util.FuzzUtil;

public class SelectLimitOffset extends LimitOffset {

    public SelectLimitOffset(SelectStatement statement) {
        super(statement);
    }

    @Override
    public void fuzz() {
        if(FuzzUtil.probability(DevTempConf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
            if(statement.confirmByRuleName(RuleName.REQUIRE_SCALA)){
                limitNode.set(1);
            }else {
                limitNode.set(FuzzUtil.randomIntFromRange(1, 100));
            }
        }

        if(FuzzUtil.probability(DevTempConf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(FuzzUtil.randomIntFromRange(1, 100));
        }
    }
}
