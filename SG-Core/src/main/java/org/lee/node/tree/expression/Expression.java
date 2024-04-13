package org.lee.node.tree.expression;

import org.lee.node.NodeTag;
import org.lee.node.base.AliasNameable;
import org.lee.node.base.TreeNode;
import org.lee.node.entry.scalar.Scalar;
import org.lee.type.base.SGType;

public class Expression implements Scalar, TreeNode, AliasNameable {
    @Override
    public boolean hasAlias() {
        return false;
    }

    @Override
    public String getAlias() {
        return null;
    }

    @Override
    public void setAlias() {

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
    public SGType getType() {
        return null;
    }
}
