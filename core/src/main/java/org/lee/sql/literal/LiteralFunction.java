package org.lee.sql.literal;

import org.lee.common.enumeration.NodeTag;
import org.lee.sql.symbol.Function;

public final class LiteralFunction extends Literal<Function> {

    private LiteralFunction(Function literalValue) {
        super(literalValue.getReturnType(), literalValue);
    }

    @Override
    public String getString() {
        return literalValue.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

}
