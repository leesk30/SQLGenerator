package org.lee.statement.expression.generator;

import org.lee.common.config.RuntimeConfiguration;
import org.lee.entry.FieldReference;
import org.lee.entry.RangeTableReference;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Qualification;
import org.lee.symbol.Comparator;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.common.util.FuzzUtil;
import org.lee.common.Pair;

public class JoinerQualificationGenerator extends RelationalGenerator<Qualification> implements QualificationGenerator {


    public JoinerQualificationGenerator(RuntimeConfiguration config, RangeTableReference left, RangeTableReference right){
        super(config, left, right);
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

    @Override
    public Pair<Scalar, Scalar> getTwoSide(TypeTag target) {
        return null;
    }

    @Override
    public Pair<Scalar, Scalar> getTwoSide(){
        if(relatedPair != null && !relatedPair.isEmpty() && FuzzUtil.probability(50)){
            return consumPair();
        }else {
            return QualificationGenerator.super.getTwoSide();
        }
    }

    public RangeTableReference getLeft() { return left; }
    public RangeTableReference getRight() { return right; }
}
