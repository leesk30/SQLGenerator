package org.lee.statement.clause.limit;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.statement.ValuesStatement;

public class ValuesLimitOffset extends LimitOffset {

    public ValuesLimitOffset(ValuesStatement statement) {
        super(statement);
    }

    @Override
    public void fuzz() {
        final int maxSize = ((ValuesStatement)statement).getValuesClause().size();
        final boolean isScalarRequired = confirm(Rule.REQUIRE_SCALA);
        if(probability(Conf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
            if(isScalarRequired){
                limitNode.set(1);
            }else {
                limitNode.set(Utility.randomIntFromRange(0, maxSize));
            }
        }

        if(!isScalarRequired && probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(Utility.randomIntFromRange(0, maxSize));
        }

    }
}
