package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Expression;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

import java.util.List;

public interface IExpressionGenerator<T extends Expression> extends Generator<T> {

    default Scalar scalarSubqueryGenerate(){
        return null;
    }

    default Scalar getLiteral(TypeTag typeTag){
        return typeTag.asMapped().generate();
    }

    default Scalar getLiteral(){
        return getLiteral(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE));
    }

    default Scalar getLiteral(int partial){
        return getLiteral(FuzzUtil.randomlyChooseFrom(TypeTag.GENERATE_PREFER_CHOOSE), partial);
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
