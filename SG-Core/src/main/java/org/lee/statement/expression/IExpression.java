package org.lee.statement.expression;

import org.lee.statement.clause.Clause;
import org.lee.statement.node.Node;
import org.lee.statement.node.TreeNode;
import org.lee.statement.entry.scalar.Scalar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public interface IExpression extends Scalar, TreeNode<Node> {
    ExpressionBuilder newBuilder();
    Node getCurrentNode();

    default boolean isLeaf(){
        return getChildNodes().isEmpty();
    }

    IExpression safeShallowCopy();
    @Override
    List<? extends IExpression> getChildNodes();

    @Override
    default Iterator<Node> walk() {
        return new ExpressionIterator(this);
    }

    class ExpressionIterator implements Iterator<Node> {
        private final IExpression expr;
        private final List<ExpressionIterator> childrenIterators = new ArrayList<>();
        private int childIndex = -1;
        private final boolean isChildrenEmpty;

        public ExpressionIterator(IExpression expr){
            this.expr = expr;
            this.isChildrenEmpty = expr.getChildNodes().isEmpty();
            expr.getChildNodes().forEach(child -> this.childrenIterators.add(new ExpressionIterator(child)));
        }

        @Override
        public boolean hasNext() {
            if(childIndex==-1){
                return true;
            }
            if(isChildrenEmpty){
                return false;
            }
            if(childrenIterators.get(childIndex).hasNext()){
                return true;
            }
            childIndex++;
            if(childIndex < childrenIterators.size()){
                return childrenIterators.get(childIndex).hasNext();
            }
            return false;
        }

        @Override
        public Node next() {
            if(childIndex == -1){
                ++childIndex;
                return expr.getCurrentNode();
            }
            return childrenIterators.get(childIndex).next();
        }
    }

}
