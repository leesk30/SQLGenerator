package org.lee.statement.expression;

import org.lee.statement.entry.scalar.Pseudo;
import org.lee.statement.node.NodeTag;
import org.lee.statement.node.Node;
import org.lee.symbol.Aggregation;
import org.lee.symbol.Signature;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Expression implements IExpression {

    private final Node current;
    private final List<Expression> childNodes;

    protected Expression(Node current){
        this.current = current;
        this.childNodes = new ArrayList<>();
    }

    protected Expression(Node current, List<Expression> childNodes){
        this.current = current;
        this.childNodes = childNodes;
    }

    public Expression newLeafExpr(Node current){
        return new Expression(current, null);
    }

    private static class Builder extends ExpressionBuilder {
        @Override
        public Expression build() {
            List<Expression> childNodes = new ArrayList<>();
            for(ExpressionBuilder builder: childExprBuilders){
                childNodes.add((Expression)builder.build());
            }
            return new Expression(this.currentNode, childNodes);
        }
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
        assert childNodes.size() == 1;
        return childNodes.get(0).getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.expression;
    }

    @Override
    public TypeTag getType() {
        return null;
    }

    @Override
    public ExpressionBuilder newBuilder() {
        return new Builder();
    }

    @Override
    public Node getCurrentNode() {
        return current;
    }

    @Override
    public IExpression safeShallowCopy() {
        return newBuilder().setCurrent(this).build();
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
        if(current instanceof Pseudo){
            return true;
        }
        return false;
    }

    public boolean isIncludingWindow(){
        return false;
    }

}
