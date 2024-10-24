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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WhereQualificationGenerator
        extends UnrelatedGenerator<Qualification>
        implements QualificationGenerator {

    protected WhereQualificationGenerator(SQLStatement statement, Scalar... scalars) {
        super(statement, scalars);
    }

    protected WhereQualificationGenerator(SQLStatement statement, List<? extends Scalar> expresssionList) {
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
    public Pair<Scalar, Scalar> getPair() {
        Set<TypeTag> moreThanOneCandidates = statistic.getGroupedType().stream().filter(
                t -> statistic.findMatch(t).size() >= 2
        ).collect(Collectors.toSet());
        if(!moreThanOneCandidates.isEmpty()){
            List<Scalar> candidates = statistic.findMatch(Utility.randomlyChooseFrom(moreThanOneCandidates));
        }
        return null;
    }

    @Override
    public Qualification generate(TypeTag require) {
        return null;
    }
}
