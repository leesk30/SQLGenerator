package org.lee.entry.literal;

import org.lee.type.TypeTag;

public class LiteralChar extends Literal<String>{
    public LiteralChar(String literalValue) {
        super(TypeTag.char_, literalValue);
    }
}
