package org.lee.entry.literal;

import org.lee.type.TypeTag;

public class LiteralDouble extends LiteralNumber<Double>{
    public LiteralDouble(double literalValue) {
        super(TypeTag.float_, literalValue);
    }

    public LiteralDouble(double literalValue, int index) {
        super(TypeTag.float_, literalValue, index);
    }
}