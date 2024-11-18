package org.lee.generator.expression;

import org.lee.generator.expression.basic.AbstractWeightedExpressionGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.entry.RangeTableReference;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

public class JoinQualificationGenerator
        extends AbstractWeightedExpressionGenerator<Qualification>
        implements QualificationGenerator {


    public JoinQualificationGenerator(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(ExpressionLocation.join, statement, left.getFieldReferences(), right.getFieldReferences());
    }

    @Override
    protected void onInitWeightedMethodInvoker() {
        registerMethod(this::predicateScalarAndScalar, 500);
        registerMethod(this::predicateFieldAndLiteral, 4);
        registerMethod(this::predicateBetweenAnd, 4);
        registerMethod(this::predicateIsNull, 4);
        if(statement.enableSubquery() && probability(50)){
            registerMethod(this::predicateSubqueryExists, 1);
            registerMethod(this::predicateInSubquery, 1);
        }
    }

    @Override
    public Qualification fallback() {
        return predicateFieldAndLiteral();
    }

}
