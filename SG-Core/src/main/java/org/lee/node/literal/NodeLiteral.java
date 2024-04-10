package org.lee.node.literal;

import org.lee.node.NodeType;
import org.lee.node.base.BaseLiteral;
import org.lee.node.base.Node;

public class NodeLiteral extends BaseLiteral<Node> {

    protected NodeLiteral(Node literalValue) {
        super(literalValue);
    }

    @Override
    public String getString() {
        return literalValue.getString();
    }

    @Override
    public NodeType getNodeType() {
        return null;
    }

}
