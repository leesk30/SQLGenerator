package org.lee.node.literal;

import org.lee.node.NodeTag;

public class StringLiteral extends BaseLiteral<String> {
    public StringLiteral(String literalValue){
        super(literalValue);

    }

    @Override
    public String getString() {
        return literalValue;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.literal;
    }

}
