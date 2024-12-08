package org.lee.sql.clause.limit;

import org.lee.common.enumeration.Conf;
import org.lee.common.enumeration.Rule;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.statement.values.ValuesStatement;

public class ValuesLimitOffset extends LimitOffset {

    public ValuesLimitOffset(ValuesStatement statement) {
        super(statement);
    }

    @Override
    public ValuesStatement retrieveParent() {
        return (ValuesStatement) statement;
    }

    @Override
    public void fuzz() {
        final int maxSize = this.retrieveParent().getValuesClause().size();
        final boolean requireScalar = confirm(Rule.REQUIRE_SCALA);
        final boolean isOneRow = this.retrieveParent().getValuesClause().getLength() == 1;
        if(requireScalar && !isOneRow){
            limitNode.set(1);
        }else if(probability(Conf.LIMIT_OFFSET_CLAUSE_FUZZ_PROBABILITY)){
            limitNode.set(RandomUtils.randomIntFromRange(0, maxSize));
        }

        if(!requireScalar && probability(Conf.LIMIT_OFFSET_WITH_OFFSET_PROBABILITY)){
            offsetNode.set(RandomUtils.randomIntFromRange(0, maxSize));
        }

    }
}
