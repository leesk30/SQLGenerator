package org.lee.sql.expression;

import org.lee.common.Assertion;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.symbol.Operator;
import org.lee.sql.type.TypeTag;

public class Assignment extends Expression  {
    private static final Operator ASSIGN = new Operator("%s = %s", 1, TypeTag.null_, TypeTag.null_, TypeTag.null_);

    public Assignment(Scalar left, Scalar right) {
        super(ASSIGN);
        this.childNodes.add(left.toCompleteExpression());
        this.childNodes.add(right.toCompleteExpression());
        Assertion.requiredTrue(this.isComplete());
    }
}
