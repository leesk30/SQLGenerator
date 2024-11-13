package org.lee.sql.type.literal;

import org.lee.sql.type.TypeTag;

public class LiteralBoolean extends Literal<Boolean> {
    public static final LiteralBoolean TRUE = new LiteralBoolean(true);
    public static final LiteralBoolean FALSE = new LiteralBoolean(false);

    protected LiteralBoolean(Boolean literalValue) {
        super(TypeTag.boolean_, literalValue);
    }

    @Override
    public String toString() {
        return literalValue ? "TRUE" : "FALSE";
    }
}
