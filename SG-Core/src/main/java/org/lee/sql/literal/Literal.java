package org.lee.sql.literal;

import org.lee.base.NodeTag;
import org.lee.common.structure.Pair;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.type.TypeTag;
import org.lee.sql.type.mapped.MappedType;

public abstract class Literal<T> implements Scalar {
    protected final MappedType<T> mappedType;
    protected T literalValue;
    protected final TypeTag literalType;
    protected Literal(TypeTag literalType, T literalValue){
        this.mappedType = literalType.<T>asMapped();
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

    public static <T> Literal<T> fromType(TypeTag typeTag){
        MappedType<T> mapped = typeTag.<T>asMapped();
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
        if(javaInstance instanceof Symbol){
            Symbol self = (Symbol) this.asJava();
            return self.getString();
        }
        return asJava().toString();
    }

    public static LiteralNull asNull(){
        return LiteralNull.NULL;
    }

    public static <T> Pair<Literal<T>, Literal<T>> orderedPair(Literal<T> v1, Literal<T> v2){
        if(!(v1.getType().isComparable() && v1.getLiteral() instanceof Comparable)){
            return null;
        }
        Comparable<T> comparable = (Comparable<T>) v1.getLiteral();
        final int result = comparable.compareTo(v2.getLiteral());
        if(result == 0){
            return null;
        }
        return result > 0 ? new Pair<>(v2, v1):new Pair<>(v1, v2);
    }
}
