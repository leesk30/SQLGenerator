package org.lee.type.literal;

import org.lee.base.NodeTag;
import org.lee.entry.scalar.Scalar;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;
import org.lee.type.literal.mapped.MappedType;

public abstract class Literal<T> implements Scalar {
    protected final MappedType<T> mappedType;
    protected T literalValue;
    protected final TypeTag literalType;
    protected Literal(TypeTag literalType, T literalValue){
        this.mappedType = literalType.asMapped();
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

//    @SuppressWarnings("unchecked")
    public static <T> Literal<T> fromType(TypeTag typeTag){
        MappedType<T> mapped = typeTag.asMapped();
        return mapped.generate();
    }

    public T asJava(){
        return literalValue;
    }

    @Override
    public String getString() {
        if(this instanceof Traceable){
            Traceable self = (Traceable) this;
            if(self.getIndex() > 0){
                return "$" + self.getIndex();
            }
        }
        if(this instanceof Inescapable){
            Inescapable self = (Inescapable) this;
            return self.getInescapeString();
        }

        T javaInstance = this.asJava();
        if(javaInstance instanceof Signature){
            Signature self = (Signature) this.asJava();
            return self.getString();
        }
        return asJava().toString();
    }

    public static LiteralNull asNull(){
        return LiteralNull.NULL;
    }
}
