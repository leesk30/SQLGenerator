package org.lee.entry.literal;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

public final class LiteralString extends Literal<String> implements Traceable, Escapable {
    public LiteralString(TypeTag literalType, String literalValue){
        super(literalType, literalValue);
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public boolean isBindingParameter() {
        return false;
    }

    @Override
    public String getUnescapeString() {
        return String.format("'%s'", literalValue);
    }
}
