package org.lee.generator.expression;

import org.lee.generator.expression.basic.AbstractWeightedExpressionGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

import java.util.List;

public class WhereQualificationGenerator
        extends AbstractWeightedExpressionGenerator<Qualification>
        implements QualificationGenerator {

    // how to construct
    public WhereQualificationGenerator(SQLStatement statement, List<? extends Scalar> expresssionList) {
        super(ExpressionLocation.where, statement, expresssionList);
    }

    // how to route
    @Override
    protected void onInitWeightedMethodInvoker() {
        registerMethod(this::predicateScalarAndScalar, 80);
        registerMethod(this::predicateFieldAndLiteral, 120);
        registerMethod(this::predicateBetweenAnd, 10);
        registerMethod(this::predicateIsNull, 2);
        if(statement.enableSubquery()){
            registerMethod(this::predicateSubqueryExists, 2);
            registerMethod(this::predicateInSubquery, 3);
        }
    }

    // how we should do when failed
    @Override
    public Qualification fallback() {
        return predicateFieldAndLiteral();
    }
}
