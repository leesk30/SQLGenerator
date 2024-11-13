package org.lee.sql.literal;

import org.lee.sql.type.TypeTag;

public class LiteralLong extends LiteralNumber<Long>{
    public LiteralLong(long literalValue) {
        super(TypeTag.bigint, literalValue);
    }

    public LiteralLong(long literalValue, int index) {
        super(TypeTag.bigint, literalValue, index);
    }
}
