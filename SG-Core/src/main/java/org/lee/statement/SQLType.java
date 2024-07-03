package org.lee.statement;

public enum SQLType {
    select,
    simple,
    scalar,
    setop,
    clause,

    update,
    delete,
    insert,
    merge,
    ;

    public boolean isDML(){
        switch (this){
            case scalar:
            case setop:
            case select:
                return false;
            default:
                return true;
        }
    }
}
