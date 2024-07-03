package org.lee.statement.entry.normalized;

import org.lee.statement.node.NodeTag;
import org.lee.statement.node.Node;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.type.TypeTag;

public class NormalizedScalar<S extends Node> implements NormalizedEntryNode<S>, Scalar {
    public S normalized;
    public NormalizedScalar(S normalized){
        this.normalized = normalized;
    }
    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public TypeTag getType() {
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
