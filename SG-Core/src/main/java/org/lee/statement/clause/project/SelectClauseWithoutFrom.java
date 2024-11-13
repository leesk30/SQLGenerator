package org.lee.statement.clause.project;

import org.lee.common.Utility;
import org.lee.expression.common.Location;
import org.lee.expression.generator.CommonExpressionGenerator;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.type.TypeTag;

import java.util.Collections;

public class SelectClauseWithoutFrom extends SelectClause{
    public SelectClauseWithoutFrom(SelectClauseStatement statement) {
        super(statement);
    }

    @Override
    protected CommonExpressionGenerator createProjectionGenerator() {
        return new CommonExpressionGenerator(Location.project, false, false, statement, Collections.emptyList());
    }

    @Override
    public final void fuzz(){
        SelectStatement statement = (SelectStatement) retrieveParent();
        final CommonExpressionGenerator generator = createProjectionGenerator();
        if(statement.getProjectTypeLimitation().isEmpty()){
            final int count = Utility.randomIntFromRange(1, 7);
            for(int i = 0; i < count; i++){
                if(Utility.probability(20)){
                    processEntry(generator.getLiteral());
                }else {
                    processEntry(generator.generate());
                }
            }
        }else {
            for(TypeTag requiredType : statement.getProjectTypeLimitation()){
                if(Utility.probability(20)){
                    processEntry(generator.generate(requiredType));
                }else {
                    processEntry(generator.generate(requiredType));
                }
            }
        }
    }
}
