package org.lee.entry.literal;

import org.lee.type.TypeTag;

public class LiteralBoolean extends Literal<Boolean> implements Inescapable {
    public static final LiteralBoolean TRUE = new LiteralBoolean(true);
    public static final LiteralBoolean FALSE = new LiteralBoolean(false);

    protected LiteralBoolean(Boolean literalValue) {
        super(TypeTag.boolean_, literalValue);
    }

    @Override
    public String toString() {
        return literalValue ? "TRUE" : "FALSE";
    }

    @Override
    public String getInescapeString() {
        return this.toString();
    }
}
