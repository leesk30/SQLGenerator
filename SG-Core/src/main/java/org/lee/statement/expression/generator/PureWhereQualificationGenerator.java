package org.lee.statement.expression.generator;

import org.lee.common.structure.Pair;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Qualification;
import org.lee.statement.support.SQLStatement;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.List;

public class PureWhereQualificationGenerator
        extends UnrelatedGenerator<Qualification>
        implements QualificationGenerator {

    protected PureWhereQualificationGenerator(SQLStatement statement, Scalar... scalars) {
        super(statement, scalars);
    }

    protected PureWhereQualificationGenerator(SQLStatement statement, List<? extends Scalar> expresssionList) {
        super(statement, expresssionList);
    }

    @Override
    public Qualification fallback() {
        return null;
    }

    @Override
    public Signature getCompareOperator(TypeTag lhs, TypeTag rhs) {
        return null;
    }

    @Override
    public Pair<Scalar, Scalar> getTwoSide(TypeTag target) {
        return null;
    }

    @Override
    Qualification generate(TypeTag require) {
        return null;
    }
}
