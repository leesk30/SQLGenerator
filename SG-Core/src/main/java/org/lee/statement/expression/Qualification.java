package org.lee.statement.expression;

import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.symbol.Signature;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Qualification implements IExpression {

    private final Node current;
    private final List<IExpression> childNodes;

    protected Qualification(Node current){
        this.current = current;
        this.childNodes = new ArrayList<>();
    }

    protected Qualification(Node current, List<IExpression> childNodes){
        this.current = current;
        this.childNodes = childNodes;
    }

    public static class Builder extends ExpressionBuilder {

        @Override
        public Qualification build() {
            List<IExpression> childNodes = new ArrayList<>();
            for(ExpressionBuilder builder: childExprBuilders){
                childNodes.add(builder.build());
            }
            return new Qualification(this.currentNode, childNodes);
        }
    }

    @Override
    public TypeTag getType() {
        return TypeTag.boolean_;
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
        return null;
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
        return NodeTag.qualification;
    }

    public Qualification toNegative(){
        boolean shouldWithParentheses = false;
        for(IExpression child: this.childNodes){
            if(child instanceof Qualification){
                shouldWithParentheses = true;
                break;
            }
        }

        if(shouldWithParentheses){
            return toNegativeWithParentheses();
        }else {
            return toNegativeWithoutParentheses();
        }
    }

    private Qualification toNegativeWithParentheses(){
        return (Qualification)newBuilder().setCurrent(StaticSymbol.PARENTHESES).addChild(toNegativeWithoutParentheses()).build();
    }

    private Qualification toNegativeWithoutParentheses(){
        return (Qualification)newBuilder().setCurrent(StaticSymbol.NOT).addChild(this).build();
    }
}
