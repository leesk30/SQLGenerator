package org.lee.statement.clause;

import org.lee.statement.SQLStatement;
import org.lee.statement.entry.literal.Literal;
import org.lee.statement.entry.literal.LiteralNumber;
import org.lee.type.TypeTag;

public abstract class LimitOffset extends Clause<Literal<Number>>{
    public final Literal<Number> limitNum = new LiteralNumber(TypeTag.int_, 0);
    public final Literal<Number> offsetNum = new LiteralNumber(TypeTag.int_, 0);
    protected LimitOffset(SQLStatement statement) {
        super(statement, 2);
        children.add(limitNum);
        children.add(offsetNum);
    }

    protected LimitOffset(SQLStatement statement, int initialCapacity) {
        super(statement, initialCapacity);
    }

    public int getOffset(){
        return offsetNum.getLiteral().intValue();
    }
    public int getLimit(){
        return limitNum.getLiteral().intValue();
    }

    @Override
    public boolean isEmpty() {
        return getOffset() == 0 && getLimit() == 0;
    }
}
