package org.lee.fuzzer.qualification;

import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.statement.expression.Qualification;
import org.lee.util.FuzzUtil;
import java.util.List;

public class JoinerQualificationGenerator extends RelationalQualificationGenerator{

    private final RangeTableReference left;
    private final RangeTableReference right;
    public JoinerQualificationGenerator(RangeTableReference left, RangeTableReference right){
        super(left, right);
        this.left = left;
        this.right = right;
    }

    protected Qualification tryWithPredicateAddition(final Qualification qualification){
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

    @Override
    public Qualification generate() {
        Qualification qualification = simplifiedHashEquals();
        qualification = tryWithPredicateAddition(qualification);
        return qualification;
    }

    public RangeTableReference getLeft() { return left; }
    public RangeTableReference getRight() { return right; }
}
