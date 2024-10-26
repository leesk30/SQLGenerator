package org.lee.statement.expression.generator;

import org.lee.common.Utility;
import org.lee.common.structure.Pair;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Qualification;
import org.lee.statement.expression.abs.QualificationGenerator;
import org.lee.statement.expression.abs.UnrelatedGenerator;
import org.lee.statement.support.SQLStatement;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WhereQualificationGenerator
        extends UnrelatedGenerator<Qualification>
        implements QualificationGenerator {

    private final GeneralExpressionGenerator complexExpressionGenerator;
    protected WhereQualificationGenerator(SQLStatement statement, Scalar... scalars) {
        super(statement, scalars);
        this.complexExpressionGenerator = new GeneralExpressionGenerator(false, false, statement, getStatistic());
    }

    public WhereQualificationGenerator(SQLStatement statement, List<? extends Scalar> expresssionList) {
        super(statement, expresssionList);
        this.complexExpressionGenerator = new GeneralExpressionGenerator(false, false, statement, getStatistic());

    }

    @Override
    public Qualification fallback() {
        return predicateFieldAndLiteral();
    }

    @Override
    public Pair<Scalar, Scalar> getPair() {
        Set<TypeTag> moreThanOneCandidates = statistic.getGroupedType().stream().filter(
                t -> statistic.findMatch(t).size() >= 2
        ).collect(Collectors.toSet());
        if(!moreThanOneCandidates.isEmpty()){
            List<Scalar> candidates = Utility.copyList(statistic.findMatch(Utility.randomlyChooseFrom(moreThanOneCandidates)));
            List<Scalar> choose = Utility.randomChooseManyFrom(2, candidates);
            return Pair.fromCollection(choose);
        }
        return null;
    }

    @Override
    final public Qualification generate(TypeTag require) {
        return generate();
    }

    @Override
    public Qualification generate(){
        //todo
        if(probability(80)){
            return predicateScalarAndScalar();
        }
        if(probability(10)){
            TypeTag target = Utility.randomlyChooseFrom(statistic.getGroupedType());
            Scalar targetScalar = Utility.randomlyChooseFrom(statistic.findMatch(target));
            if(probability(80)){
                return new Qualification(getCompareOperator(target, target)).newChild(targetScalar).newChild(getContextFreeScalar(target));
            }
            return new Qualification(getCompareOperator(target, target)).newChild(targetScalar).newChild(getContextSensitiveScalar(target));
        }
        return predicateScalarAndScalar();
    }
}
