package org.lee.node.tree.statement;

import org.lee.context.decription.SQLContext;
import org.lee.node.base.TreeNode;

public interface SQLStatement extends TreeNode {
    SQLContext getContext();
}
