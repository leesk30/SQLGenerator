package org.lee.entry;

import org.lee.base.Node;
import org.lee.base.NodeTag;

public interface NormalizedEntryWrapper<N extends Node> extends Node {
    N getWrapped();

    default NodeTag getWrappedNodeTag(){
        return getWrapped().getNodeTag();
    }
}
