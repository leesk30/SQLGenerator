package org.lee.statement.expression;

import org.lee.statement.node.Node;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.symbol.Signature;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpressionBuilder{

    protected Node currentNode = null;
    protected final List<ExpressionBuilder> childExprBuilders = new ArrayList<>();
    protected ExpressionBuilder(){}

    public ExpressionBuilder setCurrent(Scalar scalarValue){
        checkCurrentValueUnset();
        this.currentNode = scalarValue;
        return this;
    }

    public ExpressionBuilder setCurrent(Signature signature){
        checkCurrentValueUnset();
        this.currentNode = signature;
        return this;
    }

    public ExpressionBuilder setCurrent(IExpression expression){
        checkCurrentValueUnset();
        currentNode = expression.getCurrentNode();
        for(IExpression childExpression: expression.getChildNodes()){
            childExprBuilders.add(childExpression.newBuilder().setCurrent(childExpression));
        }
        return this;
    }

    public ExpressionBuilder add(IExpression expression){
        childExprBuilders.add(expression.newBuilder().setCurrent(expression));
        return this;
    }

    public ExpressionBuilder add(List<IExpression> expressions){
        for(IExpression expression: expressions){
            childExprBuilders.add(expression.newBuilder().setCurrent(expression));
        }
        return this;
    }

    public ExpressionBuilder add(ExpressionBuilder builder){
        childExprBuilders.add(builder);
        return this;
    }

    private void checkCurrentValueUnset(){
        if(currentNode != null)
            throw new RuntimeException("Current has already been set");
    }

    abstract IExpression build();
}
