package org.lee.statement.clause.project;

import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.entry.scalar.Scalar;
import org.lee.expression.common.ExprGenerators;
import org.lee.expression.generator.CommonExpressionGenerator;
import org.lee.statement.select.AbstractSimpleSelectStatement;
import org.lee.statement.select.SelectStatement;
import org.lee.statement.select.SelectType;
import org.lee.type.TypeTag;

import java.util.List;

public class SelectClauseWithinFrom extends SelectClause{
    public SelectClauseWithinFrom(SelectStatement statement) {
        super(statement);
    }

    @Override
    public void fuzz() {
        Assertion.requiredTrue(this.children.isEmpty());
        SelectStatement statement = (SelectStatement) this.statement;
        if(statement.getSelectType() == SelectType.clause){
            return;
        }
//        Clause<RangeTableReference> fromClause = ((AbstractSimpleSelectStatement) statement).getFromClause();
//        simpleFuzzProjections(fromClause.getChildNodes());
        if(statement.getProjectTypeLimitation().isEmpty()){
            nonLimitationsProjectionFuzz();
        }else {
            withLimitationsProjectionFuzz();
        }
    }

    @Override
    public AbstractSimpleSelectStatement retrieveParent(){
        return (AbstractSimpleSelectStatement) statement;
    }

    private void nonLimitationsProjectionFuzz(){
        final CommonExpressionGenerator generator = ExprGenerators.projectionFactory(this);
        final int numOfEntry = retrieveParent().getFromClause().size();
        final int numOfProjection = Utility.randomIntFromRange(numOfEntry, numOfEntry*2);
        for (int i = 0; i < numOfProjection; i++) {
            if(config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY) ||
                    config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY)){
                processEntry(generator.generate());
            }else {
                processEntry(generator.fallback());
            }
        }
    }

    private void withLimitationsProjectionFuzz(){
        final CommonExpressionGenerator generator = ExprGenerators.projectionFactory(this);
        final List<TypeTag> limitations = retrieveParent().getProjectTypeLimitation();
        for(TypeTag requiredType: limitations){
            Scalar generated;
            if(config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY) ||
                    config.probability(Conf.EXPRESSION_RECURSION_PROBABILITY)){
                generated = generator.generate(requiredType);
            }else {
                generated = generator.fallback(requiredType);
            }
            processEntry(generated);
        }
    }
}
