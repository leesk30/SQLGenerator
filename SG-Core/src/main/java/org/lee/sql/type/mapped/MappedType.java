package org.lee.sql.type.mapped;

import org.lee.base.Generator;
import org.lee.sql.literal.Literal;
import org.lee.sql.type.TypeTag;

public abstract class MappedType<T> implements Generator<Literal<T>> {
    private final Class<T> javaClass;
    private final TypeTag typeTag;
    protected MappedType(Class<T> javaClass, TypeTag typeTag){
        this.javaClass = javaClass;
        this.typeTag = typeTag;
    }

    public Class<T> getJavaClass() {
        return javaClass;
    }

    public TypeTag getTypeTag(){
        return typeTag;
    }

    public abstract Literal<T> generate();
    public abstract Literal<T> generate(int partial);

    @SuppressWarnings("unchecked")
    public static<E> MappedType<E> get(TypeTag typeTag){
        switch (typeTag){
            case int_:
                return (MappedType<E>) MappedInt.getInstance();
            case bigint:
                return (MappedType<E>) MappedLong.getInstance();
            case char_:
                return (MappedType<E>) MappedChar.getInstance();
            case decimal:
                return (MappedType<E>) MappedDecimal.getInstance();
            case timestamp:
                return (MappedType<E>) MappedTimestamp.getInstance();
            case string:
                return (MappedType<E>) MappedString.getInstance();
            case date:
                return (MappedType<E>) MappedDate.getInstance();
            case boolean_:
                return (MappedType<E>) MappedBoolean.getInstance();
            case float_:
                return (MappedType<E>) MappedDouble.getInstance();
            case null_:
                return (MappedType<E>) MappedNull.getInstance();
            default:
                throw new RuntimeException("Unrecognized type tag: " + typeTag);
        }
    }
}
