package org.lee.statement.entry.literal;

import org.lee.statement.node.NodeTag;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.type.TypeTag;

public abstract class Literal<T> implements Scalar {
    protected final T literalValue;
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
}
