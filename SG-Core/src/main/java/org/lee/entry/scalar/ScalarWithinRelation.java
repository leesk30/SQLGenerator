package org.lee.entry.scalar;

import org.lee.statement.expression.Expression;

public abstract class ScalarWithinRelation implements Scalar{

    @Override
    public Expression toExpression(){
        throw new RuntimeException("The Field or Pseudo cannot convert to expression directly. Please convert to FieldReference at first.");
    }
}
