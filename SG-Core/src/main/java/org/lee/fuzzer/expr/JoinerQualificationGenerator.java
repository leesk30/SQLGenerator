package org.lee.fuzzer.expr;

import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.Pair;

public class JoinerQualificationGenerator extends RelationalGenerator<Qualification> implements QualificationGenerator {

    private final RangeTableReference left;
    private final RangeTableReference right;

    public JoinerQualificationGenerator(RangeTableReference left, RangeTableReference right){
        super(left, right);
        this.left = left;
        this.right = right;
    }

    @Override
    public Qualification generate() {
        Qualification qualification = simplifyCompare();
        qualification = tryWithPredicateAddition(qualification);
        return qualification;
    }

    @Override
    public Qualification fallback() {
        final RangeTableReference randomRTE = FuzzUtil.randomlyChooseFrom(candidateRelations);
        final FieldReference randomFieldReference = FuzzUtil.randomlyChooseFrom(randomRTE.getFieldReferences());
        return compareToLiteral(randomFieldReference);
    }

    @Override
    public Signature getCompareOperator(TypeTag lhs, TypeTag rhs) {
        return null;
    }

    @Override
    public Pair<Scalar, Scalar> getTwoSide(TypeTag target) {
        return null;
    }

    public RangeTableReference getLeft() { return left; }
    public RangeTableReference getRight() { return right; }
}
