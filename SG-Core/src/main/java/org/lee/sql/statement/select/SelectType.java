package org.lee.sql.statement.select;

public enum SelectType {
    normal,
    simple,
    setop,
    clause,

    dynamic,
    ;
    public static final SelectType[] ALL = {
            SelectType.normal, SelectType.setop, SelectType.clause, SelectType.simple
    };

    public static final SelectType[] HEAD = {
            SelectType.normal, SelectType.setop
    };
}
