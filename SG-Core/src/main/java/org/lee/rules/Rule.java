package org.lee.rules;

import org.lee.node.NodeTag;

public interface Rule {
    default boolean pass(){
        return true;
    }
    boolean pass(NodeTag nodeTag);
}
