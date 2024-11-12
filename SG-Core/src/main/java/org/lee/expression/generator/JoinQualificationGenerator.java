package org.lee.expression.generator;

import org.lee.entry.RangeTableReference;
import org.lee.expression.Qualification;
import org.lee.expression.common.ExprGeneratorUtils;
import org.lee.expression.basic.QualificationGenerator;
import org.lee.expression.basic.RelatedGenerator;
import org.lee.expression.common.Location;
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
