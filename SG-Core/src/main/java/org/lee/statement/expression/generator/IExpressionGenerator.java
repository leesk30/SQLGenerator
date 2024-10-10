package org.lee.statement.expression.generator;

import org.lee.base.Generator;
import org.lee.common.Utility;
import org.lee.common.config.Rule;
import org.lee.entry.scalar.Scalar;
import org.lee.statement.expression.Expression;
import org.lee.statement.support.Logging;
import org.lee.statement.support.Projectable;
import org.lee.statement.support.SQLStatement;
import org.lee.statement.support.SupportRuntimeConfiguration;
import org.lee.type.TypeTag;

import java.util.Collections;
import java.util.List;

public interface IExpressionGenerator<T extends Expression>
        extends Generator<T>, SupportRuntimeConfiguration, Logging {

    SQLStatement getStatement();

    default Scalar scalarGenerate(TypeTag typeTag){
        Projectable projectable = Projectable.newRandomlyProjectable(getStatement());
        projectable.withProjectTypeLimitation(Collections.singletonList(typeTag));
        projectable.setConfig(Rule.REQUIRE_SCALA,true);
        projectable.fuzz();
        return projectable.toScalar();
    }

    default Scalar scalarGenerate(){
        return scalarGenerate(TypeTag.randomGenerateTarget());
    }

    default Scalar getLiteral(TypeTag typeTag){
        return typeTag.asMapped().generate();
    }

    default Scalar getLiteral(){
        return getLiteral(Utility.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }

    default Scalar getLiteral(int partial){
        return getLiteral(TypeTag.randomGenerateTarget(), partial);
    }

    default Scalar getLiteral(TypeTag typeTag, int partial){
        return typeTag.asMapped().generate(partial);
    }

    static boolean isContainsType(List<? extends Scalar> scalarList, TypeTag check){
        return scalarList.stream()
                .map(Scalar::getType)
                .anyMatch(exprType -> exprType == check);
    }

    static int containsHowMany(List<? extends Scalar> scalarList, TypeTag check){
        if(scalarList.isEmpty()){
            return 0;
        }
        return (int)scalarList.stream()
                .map(Scalar::getType)
                .filter(type -> type == check)
                .count();
    }
}
