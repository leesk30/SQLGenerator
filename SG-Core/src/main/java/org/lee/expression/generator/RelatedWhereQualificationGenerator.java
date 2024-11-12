package org.lee.expression.generator;

import org.lee.entry.FieldReference;
import org.lee.expression.Qualification;
import org.lee.expression.basic.QualificationGenerator;
import org.lee.expression.basic.RelatedGenerator;
import org.lee.expression.common.Location;
import org.lee.statement.support.SQLStatement;

import java.util.List;

public class RelatedWhereQualificationGenerator
        extends RelatedGenerator<Qualification>
        implements QualificationGenerator {
    /**
     * TODO
     * */


    public RelatedWhereQualificationGenerator(SQLStatement stmt, List<FieldReference> lhs, List<FieldReference> rhs) {
        super(stmt, lhs, rhs);
    }

    @Override
    public Qualification generate() {
        return null;
    }

    @Override
    public Qualification fallback() {
        return null;
    }

    @Override
    public Location getExpressionLocation() {
        return null;
    }
}
