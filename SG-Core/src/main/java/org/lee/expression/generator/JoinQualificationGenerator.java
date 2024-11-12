package org.lee.statement.expression.generator;

import org.lee.entry.RangeTableReference;
import org.lee.statement.expression.Qualification;
import org.lee.statement.expression.abs.QualificationGenerator;
import org.lee.statement.expression.abs.RelatedGenerator;
import org.lee.statement.expression.common.ExprGeneratorUtils;
import org.lee.statement.expression.common.Location;
import org.lee.statement.support.SQLStatement;

public class JoinQualificationGenerator
        extends RelatedGenerator<Qualification>
        implements QualificationGenerator {


    public JoinQualificationGenerator(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement, left.getFieldReferences(), right.getFieldReferences());
    }

    @Override
    public Qualification generate() {
        Qualification qualification = predicateScalarAndScalar();
        return ExprGeneratorUtils.tryWithPredicateAddition(qualification);
    }

    @Override
    public Qualification fallback() {
        return predicateFieldAndLiteral();
    }

    @Override
    public Location getExpressionLocation() {
        return Location.join;
    }
}
