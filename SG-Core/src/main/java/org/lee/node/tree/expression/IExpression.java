package org.lee.node.tree.expression;

import org.lee.node.base.AliasNameable;
import org.lee.node.base.Node;
import org.lee.node.base.TreeNode;
import org.lee.node.entry.scalar.Scalar;

import java.util.List;

public interface IExpression extends Scalar, TreeNode {
    ExpressionBuilder newBuilder();
    Node getCurrentNode();
    IExpression safeShallowCopy();

    @Override
    List<? extends IExpression> getChildNodes();

}
