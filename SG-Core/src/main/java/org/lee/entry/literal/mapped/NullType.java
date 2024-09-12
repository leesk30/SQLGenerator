package org.lee.entry.literal.mapped;

public final class NullType {
    public static final NullType NULL = new NullType();
    private static final String javaLiteral = "NULL";
    private NullType(){}

    @Override
    public String toString(){
        return javaLiteral;
    }
}
