package org.lee.entry.literal;

import org.lee.entry.scalar.Scalar;
import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.util.FuzzUtil;

public abstract class Literal<T> implements Scalar {
    protected T literalValue;
    protected final TypeTag literalType;
    protected Literal(TypeTag literalType, T literalValue){
        this.literalType = literalType;
        this.literalValue = literalValue;
    }
    public String getLiteralString(){
        return literalValue.toString();
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

    public static Literal<?> fromType(TypeTag typeTag){
        switch (typeTag.getCategory()){
            case NUMBER:
                return new LiteralInt(FuzzUtil.randomIntFromRange(0, 100));
            case STRING:
             default:
                return new LiteralString(typeTag, FuzzUtil.getRandomName(""));
        }
    }


    @Override
    public String getString() {
        if(this instanceof Traceable){
            Traceable self = (Traceable) this;
            if(self.getIndex() > 0){
                return "$" + self.getIndex();
            }
        }
        if(this instanceof Escapable){
            Escapable self = (Escapable) this;
            return self.getUnescapeString();
        }

        T javaInstance = this.asJava();
        if(javaInstance instanceof Signature){
            Signature self = (Signature) this.asJava();
            return self.getString();
        }
        return asJava().toString();
    }

    public T asJava(){
        return literalValue;
    }
}
