package org.lee.entry.scalar;

import org.lee.base.Node;
import org.lee.common.Assertion;
import org.lee.expression.Expression;
import org.lee.type.TypeTag;

public interface Scalar extends Node {
    TypeTag getType();

    default Expression toCompleteExpression(){
        Expression expression = new Expression(this);
        Assertion.requiredTrue(expression.isComplete());
        return expression;
    }
}

