package org.lee.expression.generator;

import org.lee.common.Utility;
import org.lee.entry.scalar.Scalar;
import org.lee.expression.Qualification;
import org.lee.expression.basic.QualificationGenerator;
import org.lee.expression.basic.UnrelatedGenerator;
import org.lee.expression.common.Location;
import org.lee.statement.support.SQLStatement;
import org.lee.type.TypeTag;

import java.util.List;

public class WhereQualificationGenerator
        extends UnrelatedGenerator<Qualification>
        implements QualificationGenerator {

    protected WhereQualificationGenerator(SQLStatement statement, Scalar... scalars) {
        super(statement, scalars);
    }

    public WhereQualificationGenerator(SQLStatement statement, List<? extends Scalar> expresssionList) {
        super(statement, expresssionList);

    }

    @Override
    public Qualification fallback() {
        return predicateFieldAndLiteral();
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

    @Override
    public Location getExpressionLocation() {
        return Location.where;
    }
}
