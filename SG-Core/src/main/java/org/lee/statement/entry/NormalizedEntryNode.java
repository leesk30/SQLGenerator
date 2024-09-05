package org.lee.statement.entry;

import org.lee.statement.node.Node;
import org.lee.statement.node.NodeTag;

public interface NormalizedEntryNode<N extends Node> extends Node {
    N getRawNode();

    default NodeTag getRawNodeTag(){
        return getRawNode().getNodeTag();
    }
}
