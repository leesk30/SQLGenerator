package org.lee.entry;

import org.lee.node.Node;
import org.lee.node.NodeTag;

public interface NormalizedEntryWrapper<N extends Node> extends Node {
    N getWrapped();

    default NodeTag getWrappedNodeTag(){
        return getWrapped().getNodeTag();
    }
}
