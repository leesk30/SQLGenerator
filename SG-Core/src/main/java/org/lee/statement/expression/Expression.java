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
import org.lee.util.DevSupplier;
import org.lee.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Expression implements Scalar, TreeNode<Expression> {

    protected final Node current;
    protected final List<Expression> childNodes;
    protected final boolean isTerminateNode;

    public Expression(Node current){
        this.isTerminateNode = !(current instanceof Signature);
        this.current = current;
        if(this.isTerminateNode){
            this.childNodes = Collections.emptyList();
        }else {
            int size = ((Signature) current).argsNum();
            this.childNodes = size > 0 ? new Vector<>(size) : Collections.emptyList();
        }
    }

    public Expression(Node current, List<Expression> childNodes){
        this.isTerminateNode = !(current instanceof Signature);
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

    protected int getTotalDegree(){
        if(isLeaf()){
            return 1;
        }
        return childNodes
                .stream()
                .map(Expression::getTotalDegree)
                .max(Integer::compare)
                .orElseThrow(DevSupplier.impossible) + 1;
    }

    public static Expression newExpression(Node current){
        return new Expression(current);
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
        if(!isTerminateNode){
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

    public Expression shallowCopy() {
        return new Expression(current, childNodes);
    }

    public boolean isCurrentComplete(){
        return isTerminateNode || ((Signature) current).argsNum() == childNodes.size();
    }

    public boolean isComplete(){
        if(!isCurrentComplete()){
            return false;
        }
        if(isLeaf()){
            return true;
        }
        return childNodes.parallelStream().allMatch(Expression::isComplete);
    }

    public boolean isCurrentAggregation(){
        return current instanceof Aggregation;
    }

    public boolean isCurrentPseudo(){
        return current instanceof Pseudo;
    }

    public boolean isCurrentWindow(){
        return current instanceof Window;
    }

    public boolean isIncludingAggregation(){
        if (isCurrentAggregation()){
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
            return isCurrentPseudo();
        }
        for(Expression child: childNodes){
            if(child.isIncludingPseudo()){
                return true;
            }
        }
        return false;
    }

    public boolean isIncludingWindow(){
        if (isCurrentWindow()){
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
        final List<Expression> expressionList = new Vector<>(getTotalDegree() * childNodes.size());
        final LinkedList<Expression> fifo = new LinkedList<>();
        fifo.add(this);
        while (!fifo.isEmpty()){
            final Expression current = fifo.removeFirst();
            fifo.addAll(current.getChildNodes());
            expressionList.add(current);
        }
        return expressionList;
    }

    public List<FieldReference> extractField(){
        return extractField(false);
    }

    public List<FieldReference> extractField(boolean parallel){
        final List<Expression> leaf = this.getLeafs();
        final Stream<Expression> stream = parallel ? leaf.parallelStream() : leaf.stream();
        return stream
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

        if(isLeaf()){
            return pair;
        }

        if(!isIncludingAggregation()){
            notInAggregator.addAll(extractField(true));
        }

        childNodes.stream().parallel().forEach(
                expression -> {
                    if(expression.isCurrentAggregation()){
                        inAggregator.addAll(expression.extractField());
                    }else if(expression.isIncludingAggregation()) {
                        final Pair<List<FieldReference>, List<FieldReference>> subpair = expression.extractAggregate();
                        inAggregator.addAll(subpair.getFirstOrElse(Collections.emptyList()));
                        notInAggregator.addAll(subpair.getSecondOrElse(Collections.emptyList()));
                    }else {
                        notInAggregator.addAll(expression.extractField());
                    }
                }
        );
        return pair;
    }

}