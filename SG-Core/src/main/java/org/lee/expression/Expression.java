package org.lee.statement.expression;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.base.TreeNode;
import org.lee.common.Assertion;
import org.lee.common.structure.Pair;
import org.lee.entry.FieldReference;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Pseudo;
import org.lee.entry.scalar.Scalar;
import org.lee.symbol.*;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Expression implements Scalar, TreeNode<Expression> {

    protected final Node current;
    protected final List<Expression> childNodes;
    protected final boolean isTerminateNode;
    protected static final Logger LOGGER = LoggerFactory.getLogger(Expression.class.getName());

    public Expression(Node current){
        Assertion.requiredNonNull(current);
        this.isTerminateNode = !(current instanceof Symbol);
        this.current = current;
        if(this.isTerminateNode){
            this.childNodes = Collections.emptyList();
        }else {
            int size = ((Symbol) current).argsNum();
            this.childNodes = size > 0 ? new ArrayList<>(size) : Collections.emptyList();
        }
    }

    public Expression(Node current, List<Expression> childNodes){
        Assertion.requiredNonNull(current);
        this.isTerminateNode = !(current instanceof Symbol);
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
                LOGGER.debug("The priority checking of signature tell we should with parentheses for this expression.");
                LOGGER.debug(String.format("The child signature is: '%s'. The parent signature is: '%s'", childOperator.getString(), parentOperator.getString()));
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
        Parentheses parentheses = isTerminateNode ? ((Scalar) current).getType().getParenthesesSymbol() : ((Symbol)current).toParentheses();
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
            return String.format(current.getString(), childNodes.toArray());
        }
        Assertion.requiredTrue(childNodes.isEmpty());
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
        if(current instanceof Symbol){
            return ((Symbol) current).getReturnType();
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
        return isTerminateNode || ((Symbol) current).argsNum() == childNodes.size();
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
                return new ArrayList<>(((Symbol) current).getArgumentsTypes());
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

        for(Expression expression: childNodes){
            if(expression.isCurrentAggregation()){
                inAggregator.addAll(expression.extractField());
            }else if(expression.isIncludingAggregation()) {
                // recursive find aggregate
                final Pair<List<FieldReference>, List<FieldReference>> subpair = expression.extractAggregate();
                inAggregator.addAll(subpair.getFirstOrElse(Collections.emptyList()));
                notInAggregator.addAll(subpair.getSecondOrElse(Collections.emptyList()));
            }else {
                notInAggregator.addAll(expression.extractField());
            }
        }
        return pair;
    }

    @Override
    public String toString(){
        // for debug
        return getString();
    }

    public Qualification toQualification(){
        if(getType() != TypeTag.boolean_){
            throw new UnsupportedOperationException(String.format("Cannot cast this expression to qualification. %s", this));
        }
        return new Qualification(current, childNodes);
    }

}
