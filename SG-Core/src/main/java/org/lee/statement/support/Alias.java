package org.lee.statement.support;

import org.lee.base.Node;

public interface Alias extends Node {
    boolean hasAlias();
    String getAlias();
    String getStringWithoutAlias();
    void setAlias();

}
