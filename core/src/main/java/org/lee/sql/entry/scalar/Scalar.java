package org.lee.sql.entry.scalar;

import org.lee.base.Node;
import org.lee.common.Assertion;
import org.lee.sql.expression.Expression;
import org.lee.sql.type.TypeTag;

public interface Scalar extends Node {
    TypeTag getType();

    default Expression toCompleteExpression(){
        Expression expression = new Expression(this);
        Assertion.requiredTrue(expression.isComplete());
        return expression;
    }
}

