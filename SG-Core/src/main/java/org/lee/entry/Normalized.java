package org.lee.entry;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.entry.relation.RangeTableEntry;
import org.lee.entry.scalar.Scalar;

public interface Normalized<N extends Node> extends Node {
    N getWrapped();

    default NodeTag getWrappedNodeTag(){
        return getWrapped().getNodeTag();
    }
}
