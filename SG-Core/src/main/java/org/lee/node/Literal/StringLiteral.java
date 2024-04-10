package org.lee.node.Literal;

import org.lee.node.NodeType;
import org.lee.node.base.BaseLiteral;

public class StringLiteral extends BaseLiteral<String> {
    public StringLiteral(String literalValue){
        super(literalValue);

    }

    @Override
    public String getString() {
        return literalValue;
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.literal;
    }

}
