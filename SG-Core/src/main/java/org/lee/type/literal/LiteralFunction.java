package org.lee.type.literal;

import org.lee.node.NodeTag;
import org.lee.symbol.Function;

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
