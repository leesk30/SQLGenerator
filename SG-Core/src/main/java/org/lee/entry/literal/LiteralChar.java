package org.lee.entry.literal;

import java.lang.Math;
import org.lee.type.TypeTag;

public class LiteralChar extends Literal<String> implements Inescapable{
    public LiteralChar(String literalValue) {
        super(TypeTag.char_, literalValue);
    }

    @Override
    public String getInescapeString() {
        return String.format("'%s'", literalValue);
    }
}
