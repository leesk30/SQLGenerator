package org.lee.node.entry.normalized;

import org.lee.node.NodeTag;
import org.lee.node.base.Node;
import org.lee.node.entry.scalar.Scalar;
import org.lee.type.base.SGType;

public class NormalizedScalar<S extends Node> implements NormalizedEntryNode<S>, Scalar {
    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public SGType getType() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public S getOriginalNode() {
        return null;
    }
}
