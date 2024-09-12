package org.lee.fuzzer.expr;

import org.lee.entry.scalar.Scalar;
import org.lee.fuzzer.Generator;
import org.lee.statement.expression.Expression;
import org.lee.type.TypeTag;

public interface ExpressionGenerator extends Generator<Expression> {

    default Scalar scalarSubqueryGenerate(){
        return null;
    }

    default Scalar getLiteral(TypeTag typeTag){
        return typeTag.asMapped().generate();
    }

    default Scalar getLiteral(TypeTag typeTag, int partial){
        return typeTag.asMapped().generate(partial);
    }
}
