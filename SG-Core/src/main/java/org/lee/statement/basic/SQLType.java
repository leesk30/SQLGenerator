package org.lee.statement.basic;

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

    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }
}