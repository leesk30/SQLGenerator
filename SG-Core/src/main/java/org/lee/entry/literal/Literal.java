package org.lee.entry.literal;

import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

public abstract class Literal<T> implements Scalar {
    protected T literalValue;
    protected final TypeTag literalType;
    protected Literal(TypeTag literalType, T literalValue){
        this.literalType = literalType;
        this.literalValue = literalValue;
    }
    public T getLiteral(){
        return literalValue;
    }

    @Override
    public TypeTag getType(){
        return literalType;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.literal;
    }

    public void set(final T literalValue){
        this.literalValue = literalValue;
    }
}
