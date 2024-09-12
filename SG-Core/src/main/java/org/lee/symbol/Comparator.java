package org.lee.symbol;

import org.lee.node.NodeTag;
import org.lee.type.TypeTag;

public enum Comparator implements Signature{
    NOT_EQ("%s != %s"),
    EQ("%s = %s"),
    GT("%s > %s"),
    GT_EQ("%s >= %s"),
    LT("%s < %s"),
    LT_EQ("%s <= %s"),

    LIKE("%s LIKE %s"),
    NOT_LIKE("%s NOT LIKE %s"),

    IS_NOT_NULL(1, "%s IS NOT NULL"),
    IS_NULL(1, "%s IS NULL"),
    BETWEEN_AND(3, "%s BETWEEN %s AND %s")
    ;

    public static final Comparator[] STRING_USABLE_COMPARATOR = {LIKE, NOT_LIKE};
    public static final Comparator[] ALL = {NOT_EQ, EQ, GT, GT_EQ, LT, LT_EQ};
    public static final Comparator[] BASE_EQ = {EQ, NOT_EQ};

    private final String symbols;
    private final int argNum;
    Comparator(int argNum, String symbols){
        this.symbols = symbols;
        this.argNum = argNum;
    }
    Comparator(String symbols){
        this.symbols = symbols;
        this.argNum = 2;
    }

    @Override
    public String getString() {
        return symbols;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.operator;
    }

    @Override
    public int argsNum() {
        return argNum;
    }

    @Override
    public TypeTag getReturnType() {
        return TypeTag.boolean_;
    }
}
