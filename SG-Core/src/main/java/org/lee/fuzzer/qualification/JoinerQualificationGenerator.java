package org.lee.fuzzer.qualification;

import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Pseudo;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.StaticSymbol;
import org.lee.util.FuzzUtil;

import java.util.List;
import java.util.stream.Collectors;

public class JoinerQualificationGenerator extends RelationalQualificationGenerator{

    private final RangeTableReference left;
    private final RangeTableReference right;
    public JoinerQualificationGenerator(RangeTableReference left, RangeTableReference right){
        super(left, right);
        this.left = left;
        this.right = right;
    }

    protected Qualification withPredicateAddition(final Qualification qualification){
        final List<FieldReference> referenceList = qualification.getLeafs()
                .stream()
                .parallel()
                .map(Expression::getCurrentNode)
                .filter(each -> each instanceof FieldReference)
                .filter(each -> {
                    final Scalar referenced = ((FieldReference) each).getReference();
                    return referenced instanceof Field || referenced instanceof Pseudo;
                })
                .map(each -> (FieldReference) each)
                .collect(Collectors.toList());
        final FieldReference addPredicateLhs = FuzzUtil.randomlyChooseFrom(referenceList);
        final Qualification qualificationLhs = compareToLiteral(addPredicateLhs);
        return new Qualification(StaticSymbol.AND)
                .newChild(qualificationLhs)
                .newChild(qualification);
    }

    @Override
    public Qualification generate() {
        // todo:
        final Qualification qualification = simplifiedHashEquals();
        // with combined const qual

        if(FuzzUtil.probability(5)){
            return withPredicateAddition(qualification);
        }
        return qualification;
    }

    public RangeTableReference getLeft() {
        return left;
    }

    public RangeTableReference getRight() {
        return right;
    }
}
