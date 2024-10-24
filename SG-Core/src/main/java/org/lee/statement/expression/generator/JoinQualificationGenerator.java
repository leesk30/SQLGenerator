package org.lee.statement.expression.generator;

import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Qualification;
import org.lee.statement.expression.abs.QualificationGenerator;
import org.lee.statement.expression.abs.RelatedGenerator;
import org.lee.statement.support.SQLStatement;
import org.lee.symbol.Comparator;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

public class JoinQualificationGenerator
        extends RelatedGenerator<Qualification>
        implements QualificationGenerator {


    public JoinQualificationGenerator(SQLStatement statement, RangeTableReference left, RangeTableReference right){
        super(statement, left, right);
    }

    @Override
    public Qualification generate() {
        Qualification qualification = simplifyCompare();
        return tryWithPredicateAddition(qualification);
    }

    @Override
    public Qualification fallback() {
        final RangeTableReference randomRTE = probability(50) ? left : right;
        final FieldReference randomFieldReference = Utility.randomlyChooseFrom(randomRTE.getFieldReferences());
        if(randomFieldReference == null){
            logger.error("Cannot find any scalar in rte.");
            logger.error("Size:" + randomRTE.getFieldReferences().size());
            throw new RuntimeException("Cannot find any scalar in rte.");
        }
        return compareToLiteral(randomFieldReference);
    }

    @Override
    public Signature getCompareOperator(TypeTag lhs, TypeTag rhs) {
//        Comparator.fastGetComparatorByCategory(lhs.getCategory());
        // todo
        return Comparator.fastGetComparatorByCategory(lhs.getCategory());
    }

    public RangeTableReference getLeft() { return left; }
    public RangeTableReference getRight() { return right; }

}
