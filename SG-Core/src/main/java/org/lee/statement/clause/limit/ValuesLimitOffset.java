package org.lee.statement.clause.limit;

import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.common.config.Rule;
import org.lee.statement.ValuesStatement;
import org.lee.statement.support.SQLStatement;

public class ValuesLimitOffset extends LimitOffset {

    public ValuesLimitOffset(ValuesStatement statement) {
        super(statement);
    }

    @Override
    public ValuesStatement retrieveStatement() {
        return (ValuesStatement) statement;
    }

    @Override
    public void fuzz() {
        final int maxSize = retrieveStatement().getValuesClause().size();
        final boolean requireScalar = confirm(Rule.REQUIRE_SCALA);
        final boolean isOneRow = retrieveStatement().getValuesClause().getLength() == 1;
        if(requireScalar && !isOneRow){
            limitNode.set(1);
        }else if(probability(Conf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
            limitNode.set(Utility.randomIntFromRange(0, maxSize));
        }

        if(!requireScalar && probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(Utility.randomIntFromRange(0, maxSize));
        }

    }
}
