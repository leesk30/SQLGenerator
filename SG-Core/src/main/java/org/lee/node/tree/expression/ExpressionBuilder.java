package org.lee.node.tree.expression;

import org.lee.node.base.Node;
import org.lee.node.entry.scalar.Scalar;
import org.lee.symbol.Signature;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpressionBuilder{

    protected Node currentValue = null;
    protected final List<ExpressionBuilder> childExprBuilders = new ArrayList<>();
    protected ExpressionBuilder(){}

    public ExpressionBuilder setCurrent(Scalar scalarValue){
        checkCurrentValueUnset();
        this.currentValue = scalarValue;
        return this;
    }

    public ExpressionBuilder setCurrent(Signature signature){
        checkCurrentValueUnset();
        this.currentValue = signature;
        return this;
    }

    public ExpressionBuilder setCurrent(IExpression expression){
        checkCurrentValueUnset();
        currentValue = expression.getCurrentNode();
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
        if(currentValue != null)
            throw new RuntimeException("Current has already been set");
    }

    abstract IExpression build();
}
