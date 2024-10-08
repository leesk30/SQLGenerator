package org.lee.type.literal;

import org.lee.type.TypeTag;

import java.math.BigDecimal;

public class LiteralDecimal extends LiteralNumber<BigDecimal> {
    public LiteralDecimal(BigDecimal literalValue) {
        super(TypeTag.decimal, literalValue);
    }

    public LiteralDecimal(BigDecimal literalValue, int index) {
        super(TypeTag.decimal, literalValue, index);
    }

}
