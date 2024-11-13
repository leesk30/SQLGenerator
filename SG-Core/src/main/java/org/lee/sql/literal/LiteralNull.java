package org.lee.sql.type.literal;

import org.lee.sql.type.TypeTag;
import org.lee.sql.type.mapped.NullType;

public class LiteralNull extends Literal<NullType> {
    protected static final LiteralNull NULL = new LiteralNull();
    protected LiteralNull() {
        super(TypeTag.null_, NullType.NULL);
    }
}
