package org.lee.type.literal;

import org.lee.type.TypeTag;

public class LiteralInt extends LiteralNumber<Integer>{
    public LiteralInt(int literalValue) {
        super(TypeTag.int_, literalValue);
    }

    public LiteralInt(int literalValue, int index) {
        super(TypeTag.int_, literalValue, index);
    }

}
