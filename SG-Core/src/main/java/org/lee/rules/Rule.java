package org.lee.rules;

import org.lee.statement.node.NodeTag;

public interface Rule {
    default boolean pass(){
        return true;
    }
    boolean pass(NodeTag nodeTag);
}
