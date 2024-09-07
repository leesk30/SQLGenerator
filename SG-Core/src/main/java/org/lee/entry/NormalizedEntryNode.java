package org.lee.entry;

import org.lee.node.Node;
import org.lee.node.NodeTag;

public interface NormalizedEntryNode<N extends Node> extends Node {
    N getRawNode();

    default NodeTag getRawNodeTag(){
        return getRawNode().getNodeTag();
    }
}
