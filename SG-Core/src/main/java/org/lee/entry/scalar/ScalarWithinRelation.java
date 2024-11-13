package org.lee.entry.scalar;

import org.lee.common.exception.InternalError;
import org.lee.expression.Expression;

public abstract class ScalarWithinRelation implements Scalar{

    @Override
    public Expression toCompleteExpression(){
        throw new InternalError("The Field or Pseudo cannot convert to expression directly. Please convert to FieldReference at first.");
    }
}
