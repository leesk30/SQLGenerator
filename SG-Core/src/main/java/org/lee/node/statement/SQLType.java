package org.lee.node.statement;

public enum SQLType {
    select,
    scalar,
    subquery,
    sublink,

    update,
    delete,
    insert,
    merge,
    ;

    public boolean isDML(){
        switch (this){
            case scalar:
            case sublink:
            case subquery:
            case select:
                return false;
            default:
                return true;
        }
    }
}
