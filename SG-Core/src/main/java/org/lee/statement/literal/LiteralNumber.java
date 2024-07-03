package org.lee.statement.literal;

import org.lee.statement.node.NodeTag;
import org.lee.type.TypeTag;

public final class LiteralNumber extends Literal<Number> implements Traceable{
    private int index;

    public LiteralNumber(TypeTag literalType, Number literalValue){
        super(literalType, literalValue);
        this.index = 0;
    }

    public LiteralNumber(TypeTag literalType, Number literalValue, int index){
        super(literalType, literalValue);
        this.index = index;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean isBindingParameter() {
        return index >= 1;
    }

    @Override
    public String getString() {
        return String.valueOf(literalValue);
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

}
