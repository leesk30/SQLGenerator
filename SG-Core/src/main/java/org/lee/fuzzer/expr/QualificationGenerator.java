package org.lee.fuzzer.expr;

import org.lee.entry.FieldReference;
import org.lee.entry.literal.Literal;
import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.Signature;
import org.lee.symbol.Comparator;
import org.lee.type.TypeCategory;
import org.lee.type.TypeTag;
import org.lee.util.DevSupplier;
import org.lee.util.FuzzUtil;
import org.lee.util.Pair;

import java.util.List;

public interface QualificationGenerator extends IExpressionGenerator<Qualification> {
    Qualification generate();
    Qualification fallback();
    Signature getCompareOperator(TypeTag lhs, TypeTag rhs);

    Pair<Scalar, Scalar> getTwoSide(TypeTag target);
    default Pair<Scalar, Scalar> getTwoSide(){
        return getTwoSide(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }

    default Qualification simplifyCompare(){
        Pair<Scalar, Scalar> twoSide = getTwoSide();
        return simplifyCompare(twoSide);
    }

    default Qualification simplifyCompare(Pair<Scalar, Scalar> pair){
        if(pair == null || !pair.getFirst().isPresent() || !pair.getSecond().isPresent()){
            return fallback();
        }
        Scalar left = pair.getFirst().get();
        Scalar right = pair.getSecond().get();
        Signature signature = getCompareOperator(left.getType(), right.getType());
        if(signature == null){
            return fallback();
        }
        Qualification qualification = new Qualification(signature);
        qualification.newChild(left);
        qualification.newChild(left);
        return qualification;
    }

    default <T> Qualification compareToLiteral(Scalar fieldReference){
        TypeTag typeTag = fieldReference.getType();
        Literal<T> literal = Literal.fromType(typeTag);
        assert typeTag.getCategory() == literal.getType().getCategory();
        Comparator comparator = Comparator.fastGetComparatorByCategory(typeTag.getCategory());
        return new Qualification(comparator)
                .newChild(fieldReference)
                .newChild(literal);
    }

    default <T> Qualification compareToRangeLiteral(Scalar fieldReference){
        if(!(fieldReference instanceof FieldReference)){
            return compareToLiteral(fieldReference);
        }
        final TypeTag typeTag = fieldReference.getType();
        final Literal<T> b1 = Literal.fromType(typeTag);
        final Literal<T> b2 = Literal.fromType(typeTag);
        Pair<Literal<T>, Literal<T>> ordered = Pair.OrderedPair(b1, b2);
        if(ordered == null){
            return fallback();
        }
        return new Qualification(Comparator.BETWEEN_AND)
                .newChild(fieldReference)
                .newChild(ordered.getFirst().orElseThrow(DevSupplier.impossible))
                .newChild(ordered.getFirst().orElseThrow(DevSupplier.impossible));
    }

    default Qualification tryWithPredicateAddition(final Qualification qualification){
        if(!FuzzUtil.probability(5)){
            return qualification;
        }
        final List<FieldReference> referenceList = qualification.extractField();
        final FieldReference addPredicateLhs = FuzzUtil.randomlyChooseFrom(referenceList);

        if(addPredicateLhs == null)
            return qualification;

        final Qualification rhs = FuzzUtil.probability(50) ?
                compareToRangeLiteral(addPredicateLhs): compareToLiteral(addPredicateLhs);

        if(FuzzUtil.probability(80)){
            return qualification.and(rhs);
        }
        return qualification.or(rhs);
    }

}
