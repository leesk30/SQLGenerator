package org.lee.statement.clause.project;

import org.lee.entry.complex.TargetEntry;
import org.lee.entry.record.Record;
import org.lee.exception.Assertion;
import org.lee.rules.RuleName;
import org.lee.statement.ValuesStatement;
import org.lee.statement.insert.InsertStatement;
import org.lee.statement.support.Projectable;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ValuesClauseForValues extends ValuesClause{
    public ValuesClauseForValues(ValuesStatement statement) {
        super(statement);
    }

    @Override
    public ValuesStatement statement(){
        return (ValuesStatement) statement;
    }

    @Override
    public void fuzz() {
        final List<TypeTag> limitations = ((Projectable)this.statement).getProjectTypeLimitation();
        final boolean withLimitations = !limitations.isEmpty();
        if(this.statement.confirmByRuleName(RuleName.REQUIRE_SCALA)){
            length = 1;
            width = withLimitations ? limitations.size(): 1;
            Assertion.requireEquals(width, 1);
        }else {
            length = FuzzUtil.randomIntFromRange(1, 10);
            width = withLimitations ? limitations.size(): FuzzUtil.randomIntFromRange(1, 7);
        }

        if(withLimitations){
            generateRecord(limitations);
        }else {
            generateRecord(getTargetTypeByWidth());
        }
    }
}