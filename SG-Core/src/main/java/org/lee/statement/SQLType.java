package org.lee.statement;

public enum SQLType {
    select,
    update,
    delete,
    insert,
    merge,

    values,
    ;

    public boolean isDML(){
        return this != SQLType.select && this != SQLType.values;
    }
}
