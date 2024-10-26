package org.lee.statement.expression.abs;

import org.lee.common.Assertion;
import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.entry.FieldReference;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.Comparator;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.type.literal.Literal;

import java.util.List;

public interface QualificationGenerator extends IExpressionGenerator<Qualification> {
    Qualification generate();
    Qualification fallback();

    Pair<Scalar, Scalar> getPair();

    default Comparator getCompareOperator(TypeTag lhs, TypeTag rhs){
        return Comparator.fastGetComparatorByCategory(lhs.getCategory());
    }

    default Qualification predicateFieldAndLiteral(){
        Scalar left = Utility.randomlyChooseFrom(getStatistic().getWholeScopeCandidates());
        Scalar right = getContextFreeScalar(left.getType());
        Signature signature = getCompareOperator(left.getType(), right.getType());
        return new Qualification(signature).newChild(left).newChild(right);
    }

    default Qualification predicateScalarAndScalar(){
        Pair<Scalar, Scalar> twoSide = getPair();
        return predicateScalarAndScalar(twoSide);
    }

    default Qualification predicateScalarAndQuery(int width){
        return null;
    }

    default Qualification predicateQueryExists(){
        return null;
    }

    default Qualification predicateScalarAndScalar(Pair<Scalar, Scalar> pair){
        if(pair == null || pair.getFirst()!=null || pair.getSecond() != null){
            return fallback();
        }
        Scalar left = pair.getFirst();
        Scalar right = pair.getSecond();
        Signature signature = getCompareOperator(left.getType(), right.getType());
        if(signature == null){
            return fallback();
        }
        return new Qualification(signature).newChild(left).newChild(right);
    }

    default <T> Qualification compareToLiteral(Scalar fieldReference){
        TypeTag typeTag = fieldReference.getType();
        Literal<T> literal = Literal.fromType(typeTag);
        // check the value is same category
        Assertion.requiredTrue(typeTag.getCategory() == literal.getType().getCategory());
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
                .newChild(ordered.getFirst())
                .newChild(ordered.getFirst());
    }

    default Qualification tryWithPredicateAddition(final Qualification qualification){
        if(!Utility.probability(5)){
            return qualification;
        }
        final List<FieldReference> referenceList = qualification.extractField();
        final FieldReference addPredicateLhs = Utility.randomlyChooseFrom(referenceList);

        if(addPredicateLhs == null)
            return qualification;

        final Qualification rhs = Utility.probability(50) ?
                compareToRangeLiteral(addPredicateLhs): compareToLiteral(addPredicateLhs);

        if(Utility.probability(80)){
            return qualification.and(rhs);
        }
        return qualification.or(rhs);
    }

    default Qualification existsPredicate(){
        return null;
    }

}
