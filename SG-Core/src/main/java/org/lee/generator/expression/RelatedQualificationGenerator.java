package org.lee.generator.expression;

import org.lee.generator.expression.basic.AbstractExpressionGenerator;
import org.lee.generator.expression.basic.QualificationGenerator;
import org.lee.generator.expression.common.ExpressionLocation;
import org.lee.generator.expression.statistic.GeneratorStatistic;
import org.lee.sql.entry.FieldReference;
import org.lee.sql.expression.Qualification;
import org.lee.sql.statement.SQLStatement;

import java.util.List;

public class RelatedQualificationGenerator
        extends AbstractExpressionGenerator<Qualification>
        implements QualificationGenerator {
    /**
     * TODO
     * */

    private final ExpressionLocation location;
    public RelatedQualificationGenerator(ExpressionLocation location, SQLStatement stmt, List<FieldReference> lhs, List<FieldReference> rhs) {
        super(stmt, lhs, rhs);
        this.location = location;
    }

    @Override
    public Qualification generate() {
        return null;
    }

    @Override
    public Qualification fallback() {
        if(location == ExpressionLocation.join){
            return predicateScalarAndScalar();
        }
        return predicateFieldAndLiteral();
    }

    @Override
    public ExpressionLocation getExpressionLocation() {
        return location;
    }
}
