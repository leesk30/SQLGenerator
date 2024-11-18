package org.lee.sql.entry;

import org.lee.base.Node;
import org.lee.base.NodeTag;

public interface Normalized<N extends Node> extends Node {
    N getWrapped();

    default NodeTag getWrappedNodeTag(){
        return getWrapped().getNodeTag();
    }
}
