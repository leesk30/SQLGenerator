package org.lee.type.literal;

import org.lee.type.literal.mapped.NullType;
import org.lee.type.TypeTag;

public class LiteralNull extends Literal<NullType> {
    protected static final LiteralNull NULL = new LiteralNull();
    protected LiteralNull() {
        super(TypeTag.null_, NullType.NULL);
    }
}
