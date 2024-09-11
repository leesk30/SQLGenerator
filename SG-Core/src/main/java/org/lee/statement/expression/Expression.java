package org.lee.statement.expression;

import org.lee.entry.FieldReference;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Pseudo;
import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
import org.lee.node.Node;
import org.lee.node.TreeNode;
import org.lee.symbol.Aggregation;
import org.lee.symbol.Signature;
import org.lee.symbol.Window;
import org.lee.type.TypeTag;
import org.lee.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Expression implements Scalar, TreeNode<Expression> {

    protected final Node current;
    protected final List<Expression> childNodes;

    public Expression(Node current){
        this.current = current;
        this.childNodes = new Vector<>();
    }

    public Expression(Node current, List<Expression> childNodes){
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
        if(current instanceof Signature){
            return ((Signature) current).getReturnType();
        }
        return ((Scalar) current).getType();
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
        getChildNodes().forEach(child -> leafs.addAll(child.getLeafs()));
        return leafs;
    }

    @Override
    public Stream<Expression> walk() {
        return midTraverseExpression().stream();
    }

    protected List<Expression> midTraverseExpression(){
        final List<Expression> expressionList = new Vector<>();
        final List<Expression> fifo = new LinkedList<>();
        fifo.add(this);
        while (!fifo.isEmpty()){
            final Expression current = fifo.remove(0);
            fifo.addAll(current.getChildNodes());
            expressionList.add(current);
        }
        return expressionList;
    }

    public List<FieldReference> extractFieldReferences(){
        return this.getLeafs()
                .stream()
                .parallel()
                .map(Expression::getCurrentNode)
                .filter(each -> each instanceof FieldReference)
                .filter(each -> {
                    final Scalar referenced = ((FieldReference) each).getReference();
                    return referenced instanceof Field || referenced instanceof Pseudo;
                })
                .map(each -> (FieldReference) each)
                .collect(Collectors.toList());
    }

    public Pair<List<FieldReference>, List<FieldReference>> extractAggregate(){
        final List<FieldReference> inAggregator = new Vector<>();
        final List<FieldReference> notInAggregator = new Vector<>();
        final Pair<List<FieldReference>, List<FieldReference>> pair = new Pair<>(inAggregator, notInAggregator);

        return pair;
    }

}
