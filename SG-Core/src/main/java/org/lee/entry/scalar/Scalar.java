package org.lee.entry.scalar;

import org.lee.node.Node;
import org.lee.statement.expression.Expression;
import org.lee.type.TypeTag;

public interface Scalar extends Node {
    TypeTag getType();

    default Expression toExpression(){
        return new Expression(this);
    }
}

