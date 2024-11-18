package org.lee.sql.clause.project;

import org.lee.common.Assertion;
import org.lee.common.enumeration.Conf;
import org.lee.common.utils.RandomUtils;
import org.lee.generator.expression.CommonExpressionGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.statement.select.AbstractSimpleSelectStatement;
import org.lee.sql.statement.select.SelectStatement;
import org.lee.sql.statement.select.SelectType;
import org.lee.sql.type.TypeTag;

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
        final int numOfProjection = RandomUtils.randomIntFromRange(numOfEntry, numOfEntry*2);
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
