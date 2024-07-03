package org.lee.rules;

import org.lee.statement.node.NodeTag;

public class EnableCTERules implements Rule{
    @Override
    public boolean pass() {
        return Rule.super.pass();
    }

    @Override
    public boolean pass(NodeTag nodeTag) {
        return false;
    }
}
