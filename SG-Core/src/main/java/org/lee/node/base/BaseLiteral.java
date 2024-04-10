package org.lee.node.base;

public abstract class BaseLiteral<T> implements Node {

    protected final T literalValue;
    protected BaseLiteral(T literalValue){
        this.literalValue = literalValue;
    }

    public T getLiteral(){
        return literalValue;
    }
}
