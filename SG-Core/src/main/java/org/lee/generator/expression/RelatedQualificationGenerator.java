package org.lee.expression.generator;

import org.lee.entry.FieldReference;
import org.lee.expression.Qualification;
import org.lee.expression.basic.QualificationGenerator;
import org.lee.expression.basic.RelatedGenerator;
import org.lee.expression.common.Location;
import org.lee.statement.support.SQLStatement;

import java.util.List;

public class RelatedQualificationGenerator
        extends RelatedGenerator<Qualification>
        implements QualificationGenerator {
    /**
     * TODO
     * */

    private final Location location;

    public RelatedQualificationGenerator(Location location, SQLStatement stmt, List<FieldReference> lhs, List<FieldReference> rhs) {
        super(stmt, lhs, rhs);
        this.location = location;
    }

    @Override
    public Qualification generate() {
        return null;
    }

    @Override
    public Qualification fallback() {
        if(location == Location.join){
            return predicateScalarAndScalar();
        }
        return predicateFieldAndLiteral();
    }

    @Override
    public Location getExpressionLocation() {
        return location;
    }
}
