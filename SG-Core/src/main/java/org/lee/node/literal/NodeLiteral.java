package org.lee.node.literal;

import org.lee.node.NodeTag;
import org.lee.node.base.Node;

public class NodeLiteral<T extends Node> extends BaseLiteral<T> {

    protected NodeLiteral(T literalValue) {
        super(literalValue);
    }

    @Override
    public String getString() {
        return literalValue.getString();
    }

    @Override
    public NodeTag getNodeType() {
        return null;
    }

}
