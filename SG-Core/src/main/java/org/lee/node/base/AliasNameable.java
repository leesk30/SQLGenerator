package org.lee.node.base;

import java.math.BigInteger;

public interface AliasNameable extends Node{
    boolean hasAlias();
    String getAlias();
    void setAlias();
}
