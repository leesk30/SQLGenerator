package org.lee.fuzzer.expr;

import org.lee.entry.FieldReference;
import org.lee.entry.literal.Literal;
import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.Signature;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;
import org.lee.util.Pair;

import java.util.List;

public interface QualificationGenerator extends Generator<Qualification> {
    Qualification generate();
    Qualification fallback();

    Signature getCompareOperator(TypeTag lhs, TypeTag rhs);
    Pair<Scalar, Scalar> getTwoSide(TypeTag target);
    default Pair<Scalar, Scalar> getTwoSide(){
        return getTwoSide(FuzzUtil.randomlyChooseFrom(TypeTag.ALL));
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
        return new Qualification(StaticSymbol.EQUALS)
                .newChild(fieldReference)
                .newChild(literal);
    }

    default <T> Qualification compareToRangeLiteral(Scalar fieldReference){
        final TypeTag typeTag = fieldReference.getType();
        final Literal<T> b1 = Literal.fromType(typeTag);
        final Literal<T> b2 = Literal.fromType(typeTag);
        Literal<T> lhs;
        Literal<T> rhs;
        if(!(b1.getLiteral() instanceof Comparable) && b1.getType().isComparable()){
            // fallback to
            return compareToLiteral(fieldReference);
        }
        assert b2.getLiteral() instanceof Comparable;
        Comparable<T> comparable = (Comparable<T>) b1.getLiteral();
        if(comparable.compareTo(b2.getLiteral()) > 0){
            lhs = b2;
            rhs = b1;
        }else {
            lhs = b1;
            rhs = b2;
        }
        return new Qualification(StaticSymbol.BETWEEN).newChild(fieldReference).newChild(lhs).newChild(rhs);
    }

    default Qualification tryWithPredicateAddition(final Qualification qualification){
        if(!FuzzUtil.probability(5)){
            return qualification;
        }
        final List<FieldReference> referenceList = qualification.extractFieldReferences();
        final FieldReference addPredicateLhs = FuzzUtil.randomlyChooseFrom(referenceList);

        if(addPredicateLhs == null)
            return qualification;

        final Qualification rhs = FuzzUtil.probability(50) ?
                compareToRangeLiteral(addPredicateLhs): compareToLiteral(addPredicateLhs);
        return qualification.and(rhs);
    }

}
