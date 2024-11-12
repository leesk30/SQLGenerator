package org.lee.entry.scalar;

import org.lee.base.Node;
import org.lee.expression.Expression;
import org.lee.type.TypeTag;

public interface Scalar extends Node {
    TypeTag getType();

    default Expression toExpression(){
        return new Expression(this);
    }
}

