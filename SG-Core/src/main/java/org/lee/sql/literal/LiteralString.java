package org.lee.sql.type.literal;

import org.lee.sql.type.TypeTag;

public final class LiteralString extends Literal<String> implements Traceable, Inescapable {
    public LiteralString(String literalValue){
        super(TypeTag.string, literalValue);
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
    public String getInescapeString() {
        return String.format("'%s'", literalValue);
    }
}
