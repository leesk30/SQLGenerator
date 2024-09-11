package org.lee.type;

public enum TypeCategory {
    NUMBER(true, true),
    STRING(true, false),
    DATE(true, false),
    BOOLEAN(false, false),
    TIMESTAMP(true, false),
    NIL(false, false),
    ;

    private final boolean comparable;
    private final boolean computable;
    private TypeCategory(boolean comparable, boolean computable){
        this.comparable = comparable;
        this.computable = computable;
    }

    public boolean isComparable() {
        return comparable;
    }

    public boolean isComputable() {
        return computable;
    }
}
