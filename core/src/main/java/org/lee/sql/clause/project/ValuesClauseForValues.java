package org.lee.sql.clause.project;

import org.lee.common.Assertion;
import org.lee.common.enumeration.Rule;
import org.lee.common.utils.RandomUtils;
import org.lee.sql.statement.values.ValuesStatement;
import org.lee.sql.type.TypeTag;

import java.util.List;

public class ValuesClauseForValues extends ValuesClause{
    public ValuesClauseForValues(ValuesStatement statement) {
        super(statement);
    }

    @Override
    public ValuesStatement retrieveParent(){
        return (ValuesStatement) statement;
    }

    @Override
    public void fuzz() {
        final List<TypeTag> limitations = this.retrieveParent().getProjectTypeLimitation();
        final boolean withLimitations = !limitations.isEmpty();
        if(this.statement.confirm(Rule.REQUIRE_SCALA)){
            length = 1;
            width = withLimitations ? limitations.size(): 1;
            Assertion.requiredEquals(width, 1);
        }else {
            length = RandomUtils.randomIntFromRange(1, 10);
            width = withLimitations ? limitations.size(): RandomUtils.randomIntFromRange(1, 7);
        }

        if(withLimitations){
            generateRecord(limitations);
        }else {
            generateRecord(getTargetTypeByWidth());
        }
    }
}
