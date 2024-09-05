package org.lee.statement.entry.literal;

import org.lee.statement.node.NodeTag;
import org.lee.type.TypeTag;

public final class LiteralString extends Literal<String> implements Traceable {
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

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public boolean isBindingParameter() {
        return false;
    }
}
