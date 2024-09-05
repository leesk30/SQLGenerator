package org.lee.statement.select;

public enum SelectType {
    normal,
    simple,
    scalar,
    setop,
    clause,
    ;
    public static final SelectType[] ALL = {SelectType.normal, SelectType.setop, SelectType.clause, SelectType.simple};
}
