package org.lee.statement.expression;

import org.lee.entry.FieldReference;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Pseudo;
import org.lee.entry.scalar.Scalar;
import org.lee.common.exception.Assertion;
import org.lee.base.NodeTag;
import org.lee.base.Node;
import org.lee.base.TreeNode;
import org.lee.symbol.*;
import org.lee.type.TypeTag;
import org.lee.common.Pair;

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
            this.childNodes = size > 0 ? new ArrayList<>(size) : Collections.emptyList();
        }
    }

    public Expression(Node current, List<Expression> childNodes){
        this.isTerminateNode = !(current instanceof Signature);
        this.current = current;
        this.childNodes = childNodes;
    }

    public Expression newChild(Node current){
        if(current instanceof Expression){
            tryParenthesesAddChild((Expression)current);
        }else {
            tryParenthesesAddChild(new Expression(current));
        }
        return this;
    }

    private void tryParenthesesAddChild(Expression childExpression){
        if(this.current instanceof Operator && childExpression.current instanceof Operator){
            Operator parentOperator = (Operator) this.current;
            Operator childOperator = (Operator) childExpression.current;
            // Expression(Expression(a + b) * c) -> (a + b) * c
            // Expression(a + Expression(b * c)) -> a + b * c
            if(childOperator.getSignaturePriority() < parentOperator.getSignaturePriority()){
                this.childNodes.add(childExpression.toWithParenthesesExpression());
                return;
            }
        }
        this.childNodes.add(childExpression);
    }

    protected int getTotalDegree(){
        if(isLeaf()){
            return 1;
        }
        return childNodes
                .stream()
                .map(Expression::getTotalDegree)
                .max(Integer::compare)
                .orElseThrow(Assertion.IMPOSSIBLE) + 1;
    }

    public Expression toWithParenthesesExpression(){
        Parentheses parentheses = isTerminateNode? ((Scalar) current).getType().getParenthesesSymbol() : ((Signature)current).toParentheses();
        Expression newer = newExpression(parentheses) ;
        newer.childNodes.addAll(childNodes);
        return newer;
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
    public Expression toExpression(){
        return this;
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
        return childNodes.stream().allMatch(Expression::isComplete);
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
        if (!isTerminateNode && isCurrentAggregation()){
            return true;
        }
        return childNodes.stream().anyMatch(Expression::isIncludingAggregation);
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
        final List<Expression> leafs = new ArrayList<>();
        getChildNodes().forEach(child -> leafs.addAll(child.getLeafs()));
        return leafs;
    }

    public List<TypeTag> getLeafRequired(){
        if(isLeaf()){
            if(!isTerminateNode){
                return new ArrayList<>(((Signature) current).getArgumentsTypes());
            }else {
                return Collections.emptyList();
            }
        }
        final List<TypeTag> types = new ArrayList<>();
        childNodes.forEach(child -> types.addAll(child.getLeafRequired()));
        return types;
    }

    @Override
    public Stream<Expression> walk() {
        return midTraverseExpression().stream();
    }

    protected List<Expression> midTraverseExpression(){
        final List<Expression> expressionList = new ArrayList<>(getTotalDegree() * childNodes.size());
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
        if(isLeaf() && isTerminateNode && current instanceof FieldReference){
            return Collections.singletonList((FieldReference) current);
        }

        final List<Expression> leaf = this.getLeafs();
        return leaf.stream()
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
        final List<FieldReference> inAggregator = new ArrayList<>();
        final List<FieldReference> notInAggregator = new ArrayList<>();
        final Pair<List<FieldReference>, List<FieldReference>> pair = new Pair<>(inAggregator, notInAggregator);

        if(isCurrentAggregation()){
            inAggregator.addAll(extractField());
            return pair;
        }

        if(!isIncludingAggregation()){
            notInAggregator.addAll(extractField());
            return pair;
        }

        childNodes.forEach(
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

    @Override
    public String toString(){
        // for debug
        return getString();
    }

}
