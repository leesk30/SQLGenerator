package org.lee.sql.type.literal;

import org.lee.sql.type.TypeTag;

public class LiteralDouble extends LiteralNumber<Double>{
    public LiteralDouble(double literalValue) {
        super(TypeTag.float_, literalValue);
    }

    public LiteralDouble(double literalValue, int index) {
        super(TypeTag.float_, literalValue, index);
    }

    @Override
    public String getString() {
        return literalValue + "D";
    }
}
