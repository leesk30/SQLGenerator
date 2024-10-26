package org.lee.statement.clause.project;

import org.lee.common.Utility;
import org.lee.statement.expression.generator.GeneralExpressionGenerator;
import org.lee.statement.select.SelectClauseStatement;
import org.lee.type.TypeTag;

public class SelectClauseWithoutFrom extends SelectClause{
    public SelectClauseWithoutFrom(SelectClauseStatement statement) {
        super(statement);
    }



    @Override
    public final void fuzz(){
        SelectClauseStatement statement = (SelectClauseStatement) this.statement;
        final GeneralExpressionGenerator generator = GeneralExpressionGenerator.emptyCandidateExpressionGenerator(this.statement);
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
