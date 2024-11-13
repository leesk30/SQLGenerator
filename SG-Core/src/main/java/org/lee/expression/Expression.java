package org.lee.expression;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.common.Assertion;
import org.lee.entry.scalar.Scalar;
import org.lee.symbol.*;
import org.lee.type.TypeTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Expression implements IExpression<Expression> {

    protected final Node current;
    protected final List<IExpression<Expression>> childNodes;
    protected final boolean isTerminateNode;
    protected static final Logger LOGGER = LoggerFactory.getLogger(Expression.class.getName());

    public static Expression newExpression(Node current){
        return new Expression(current);
    }

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

    public Expression(Node current, List<IExpression<Expression>> childNodes){
        Assertion.requiredNonNull(current);
        this.isTerminateNode = !(current instanceof Symbol);
        this.current = current;
        this.childNodes = childNodes;
    }

    @Override
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
                this.childNodes.add(childExpression.toWithParentheses());
                return;
            }
        }
        this.childNodes.add(childExpression);
    }

    @Override
    public Expression toWithParentheses(){
        Parentheses parentheses = isTerminateNode ? ((Scalar) current).getType().getParenthesesSymbol() : ((Symbol)current).toParentheses();
        Expression newer = newExpression(parentheses) ;
        newer.childNodes.addAll(childNodes);
        return newer;
    }

    @Override
    public List<IExpression<Expression>> getChildNodes() {
        return childNodes;
    }

    @Override
    public boolean isTerminateNode() {
        return isTerminateNode;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.expression;
    }

    @Override
    public Expression toCompleteExpression(){
        Assertion.requiredTrue(isComplete());
        return this;
    }

    @Override
    public Node getCurrent() {
        return current;
    }

    @Override
    public List<Expression> getLeafs(){
        if(isLeaf()){
            return Collections.singletonList(this);
        }
        final List<Expression> leafs = new ArrayList<>();
        getChildNodes().forEach(child -> leafs.addAll(child.getLeafs()));
        return leafs;
    }

    @Override
    public String toString(){
        // for debug
        return getString();
    }

    public Qualification toCompleteQualification(){
        if(!isComplete()){
            throw new UnsupportedOperationException("Cannot add incomplete qualification or expression in filter.");
        }
        if(getType() != TypeTag.boolean_){
            throw new UnsupportedOperationException(String.format("Cannot cast this expression to qualification. %s", this));
        }
        return new Qualification(current, childNodes);
    }

}
