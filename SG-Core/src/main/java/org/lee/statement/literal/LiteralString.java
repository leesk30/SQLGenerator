package org.lee.statement.literal;

import org.lee.statement.node.NodeTag;
import org.lee.type.TypeTag;

public final class LiteralString extends Literal<String> {
    public LiteralString(TypeTag literalType, String literalValue){
        super(literalType, literalValue);
    }

    @Override
    public String getString() {
        return literalValue;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.literal;
    }

}
