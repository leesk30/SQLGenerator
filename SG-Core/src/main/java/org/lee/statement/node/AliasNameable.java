package org.lee.statement.node;

public interface AliasNameable extends Node{
    boolean hasAlias();
    String getAlias();
    void setAlias();
}
