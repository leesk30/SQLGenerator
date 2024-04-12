package org.lee.node.statement;

public enum SQLType {
    select,
    scalar,
    subquery,
    collection,

    update,
    delete,
    insert,
    merge,
    ;

    public boolean isDML(){
        switch (this){
            case scalar:
            case collection:
            case subquery:
            case select:
                return false;
            default:
                return true;
        }
    }
}
