package org.lee.generator.expression;

import org.lee.common.NamedLoggers;
import org.lee.common.exception.ValueCheckFailedException;
import org.lee.generator.expression.basic.AbstractWeightedExpressionGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;
import org.slf4j.Logger;

import java.util.List;

public class CommonQualificationGenerator
        extends AbstractWeightedExpressionGenerator<Qualification>
        implements QualificationGenerator {

    private static final Logger LOGGER = NamedLoggers.getCoreLogger(CommonQualificationGenerator.class);

    public CommonQualificationGenerator(ExpressionLocation location, SQLStatement stmt, List<FieldReference> lhs, List<FieldReference> rhs) {
        super(location, stmt, lhs, rhs);
    }

    public CommonQualificationGenerator(ExpressionLocation location, SQLStatement stmt, List<FieldReference> oneSide) {
        super(location, stmt, oneSide);
    }


    @Override
    public Qualification fallback() {
        if(statistic.isEmpty()){
            LOGGER.error("Cannot find any candidates whiling process fallback!!!");
            if(statement.enableSubquery()){
                return predicateSubqueryExists();
            }else {
                return probability(50) ? Qualification.ALWAYS_TRUE : Qualification.ALWAYS_FALSE;
            }
        }
        return predicateFieldAndLiteral();
    }

    @Override
    protected void onInitWeightedMethodInvoker() {
        if(expressionLocation == ExpressionLocation.join){
            registerMethod(this::predicateScalarAndScalar, 500);
            registerMethod(this::predicateFieldAndLiteral, 4);
            registerMethod(this::predicateBetweenAnd, 4);
            registerMethod(this::predicateIsNull, 4);
            if(statement.enableSubquery() && probability(50)){
                registerMethod(this::predicateSubqueryExists, 1);
                registerMethod(this::predicateInSubquery, 1);
            }
            return;
        }

        if(expressionLocation == ExpressionLocation.where){
            registerMethod(this::predicateScalarAndScalar, 80);
            registerMethod(this::predicateFieldAndLiteral, 120);
            registerMethod(this::predicateBetweenAnd, 10);
            registerMethod(this::predicateIsNull, 2);
            if(statement.enableSubquery()){
                registerMethod(this::predicateSubqueryExists, 2);
                registerMethod(this::predicateInSubquery, 3);
            }
            return;
        }

        throw new ValueCheckFailedException("Cannot build weighted generator for expression location: " + expressionLocation);

    }
}
