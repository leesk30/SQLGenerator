package org.lee.statement.expression;

import org.lee.entry.scalar.Pseudo;
import org.lee.node.NodeTag;
import org.lee.node.Node;
import org.lee.symbol.Aggregation;
import org.lee.symbol.Signature;
import org.lee.symbol.Window;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Expression implements IExpression {

    private final Node current;
    private final List<IExpression> childNodes;

    protected Expression(Node current){
        this.current = current;
        this.childNodes = new ArrayList<>();
    }

    protected Expression(Node current, List<IExpression> childNodes){
        this.current = current;
        this.childNodes = childNodes;
    }

    public Expression newLeafExpr(Node current){
        return new Expression(current, null);
    }

    public static class Builder extends ExpressionBuilder {
        @Override
        public Expression build() {
            List<IExpression> childNodes = new ArrayList<>();
            for(ExpressionBuilder builder: childExprBuilders){
                childNodes.add(builder.build());
            }
            return new Expression(this.currentNode, childNodes);
        }
    }

    @Override
    public List<IExpression> getChildNodes() {
        return childNodes;
    }

    @Override
    public String getString() {
        if(current instanceof Signature){
            return String.format(current.getString(), childNodes.stream().map(IExpression::getString).toArray());
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

        for(IExpression child: childNodes){
            if(child instanceof Expression && ((Expression)child).isIncludingAggregation()){
                return true;
            }
        }
        return false;
    }

    public boolean isIncludingPseudo(){
        if(isLeaf()){
            return current instanceof Pseudo;
        }
        for(IExpression child: childNodes){
            if(child instanceof Expression && ((Expression)child).isIncludingPseudo()){
                return true;
            }
        }
        return false;
    }

    public boolean isIncludingWindow(){
        if (current instanceof Window){
            return true;
        }

        for(IExpression child: childNodes){
            if(child instanceof Expression && ((Expression)child).isIncludingWindow()){
                return true;
            }
        }
        return false;
    }

}
