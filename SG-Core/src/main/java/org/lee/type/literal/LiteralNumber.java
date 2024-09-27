package org.lee.type.literal;

import org.lee.type.TypeTag;

public abstract class LiteralNumber<T extends Number> extends Literal<T> implements Traceable{
    private int index;

    public LiteralNumber(TypeTag literalType, T literalValue){
        super(literalType, literalValue);
        this.index = 0;
    }

    public LiteralNumber(TypeTag literalType, T literalValue, int index){
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

}
