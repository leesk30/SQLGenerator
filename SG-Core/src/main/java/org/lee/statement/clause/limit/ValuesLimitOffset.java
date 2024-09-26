package org.lee.statement.clause.limit;

import org.lee.common.DevTempConf;
import org.lee.common.config.RuleName;
import org.lee.statement.ValuesStatement;
import org.lee.util.FuzzUtil;

public class ValuesLimitOffset extends LimitOffset {

    public ValuesLimitOffset(ValuesStatement statement) {
        super(statement);
    }

    @Override
    public void fuzz() {
        int maxSize = ((ValuesStatement)statement).getValuesClause().size();
        if(FuzzUtil.probability(DevTempConf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
            if(statement.confirm(RuleName.REQUIRE_SCALA)){
                limitNode.set(1);
            }else {
                limitNode.set(FuzzUtil.randomIntFromRange(0, maxSize));
            }
        }

        if(FuzzUtil.probability(DevTempConf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(FuzzUtil.randomIntFromRange(0, maxSize));
        }

    }
}
