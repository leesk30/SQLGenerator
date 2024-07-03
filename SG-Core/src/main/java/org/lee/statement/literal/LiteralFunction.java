package org.lee.statement.literal;

import org.lee.statement.node.NodeTag;
import org.lee.symbol.BuiltinFunction;

public final class LiteralFunction extends Literal<BuiltinFunction> {

    private LiteralFunction(BuiltinFunction literalValue) {
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
