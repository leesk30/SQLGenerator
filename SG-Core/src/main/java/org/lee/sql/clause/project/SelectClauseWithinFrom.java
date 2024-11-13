package org.lee.sql.clause.project;

import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.config.Conf;
import org.lee.entry.FieldReference;
import org.lee.entry.scalar.Scalar;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.CommonExpressionGenerator;
import org.lee.sql.select.AbstractSimpleSelectStatement;
import org.lee.sql.select.SelectStatement;
import org.lee.sql.select.SelectType;
import org.lee.type.TypeTag;

import java.util.ArrayList;
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

    @Override
    protected CommonExpressionGenerator createProjectionGenerator() {
        AbstractSimpleSelectStatement simple = this.retrieveParent();
        List<FieldReference> fieldReferences = new ArrayList<>();
        simple.getFromClause().getChildNodes().forEach(ref -> fieldReferences.addAll(ref.getFieldReferences()));
        return new CommonExpressionGenerator(ExpressionLocation.project, true, false, simple, fieldReferences);
    }

    private void nonLimitationsProjectionFuzz(){
        final CommonExpressionGenerator generator = createProjectionGenerator();
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
        final CommonExpressionGenerator generator = createProjectionGenerator();
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
