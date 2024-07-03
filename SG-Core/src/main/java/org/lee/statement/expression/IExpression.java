package org.lee.statement.expression;

import org.lee.statement.node.Node;
import org.lee.statement.node.TreeNode;
import org.lee.statement.entry.scalar.Scalar;

import java.util.List;

public interface IExpression extends Scalar, TreeNode {
    ExpressionBuilder newBuilder();
    Node getCurrentNode();

    default boolean isLeaf(){
        return getChildNodes().isEmpty();
    }

    IExpression safeShallowCopy();
    @Override
    List<? extends IExpression> getChildNodes();

}
