package org.lee.sql.type.literal;

public interface Traceable {
    int getIndex();
    boolean isBindingParameter();
}
