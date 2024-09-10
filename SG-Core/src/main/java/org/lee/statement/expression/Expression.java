package org.lee.statement.expression;

import org.lee.common.SGException;
import org.lee.entry.scalar.Pseudo;
import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
import org.lee.node.Node;
import org.lee.node.TreeNode;
import org.lee.symbol.Aggregation;
import org.lee.symbol.Signature;
import org.lee.symbol.Window;
import org.lee.type.TypeTag;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Expression implements Scalar, TreeNode<Node> {

    protected final Node current;
    protected final List<Expression> childNodes;

    protected Expression(Node current){
        this.current = current;
        this.childNodes = new Vector<>();
    }

    protected Expression(Node current, List<Expression> childNodes){
        this.current = current;
        this.childNodes = childNodes;
    }

    public Expression newChild(Node current){
        if(current instanceof Expression){
            this.childNodes.add((Expression)current);
        }else {
            this.childNodes.add(new Expression(current));
        }
        return this;
    }

    public void extractChildren(Expression expression){
        this.childNodes.addAll(expression.getChildNodes());
    }

    @Override
    public List<Expression> getChildNodes() {
        return childNodes;
    }

    @Override
    public String getString() {
        if(current instanceof Signature){
            return String.format(current.getString(), childNodes.stream().map(Expression::getString).toArray());
        }
        assert childNodes.isEmpty();
        return current.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.expression;
    }

    @Override
    public TypeTag getType() {
        return null;
    }

    public Node getCurrentNode() {
        return current;
    }

    public Expression safeShallowCopy() {
        return new Expression(current, childNodes);
    }

    public boolean isIncludingAggregation(){
        if (current instanceof Aggregation){
            return true;
        }

        for(Expression child: childNodes){
            if(child.isIncludingAggregation()){
                return true;
            }
        }
        return false;
    }

    public boolean isIncludingPseudo(){
        if(isLeaf()){
            return current instanceof Pseudo;
        }
        for(Expression child: childNodes){
            if(child.isIncludingPseudo()){
                return true;
            }
        }
        return false;
    }

    public boolean isIncludingWindow(){
        if (current instanceof Window){
            return true;
        }

        for(Expression child: childNodes){
            if(child.isIncludingWindow()){
                return true;
            }
        }
        return false;
    }

    static class ExpressionIterator implements Iterator<Node> {
        private final Expression expr;
        private final List<ExpressionIterator> childrenIterators = new Vector<>();
        private final AtomicInteger childIndex = new AtomicInteger(-1);

        public ExpressionIterator(Expression expr){
            this.expr = expr;
            expr.getChildNodes().forEach(child -> this.childrenIterators.add((ExpressionIterator) child.walk()));
        }

        @Override
        public boolean hasNext() {
            if(childIndex.get() ==-1 || childrenIterators.get(childIndex.get()).hasNext()){
                return true;
            }
            if(expr.isLeaf()){
                return false;
            }
            if(childIndex.incrementAndGet() < childrenIterators.size()){
                return childrenIterators.get(childIndex.get()).hasNext();
            }
            return false;
        }

        @Override
        public Node next() {
            if(childIndex.get() != -1){
                return childrenIterators.get(childIndex.get()).next();
            }
            childIndex.incrementAndGet();
            return expr.getCurrentNode();
        }
    }

    public boolean isLeaf(){
        return this.childNodes.isEmpty();
    }

    public Node getCurrent() {
        return current;
    }

    public List<Expression> getLeafs(){
        if(isLeaf()){
            return Collections.singletonList(this);
        }
        final List<Expression> leafs = new Vector<>();
        getChildNodes().stream().parallel().forEachOrdered(child -> leafs.addAll(child.getLeafs()));
        return leafs;
    }

    @Override
    public Iterator<Node> walk() {
        return new ExpressionIterator(this);
    }
}
