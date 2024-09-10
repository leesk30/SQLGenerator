package org.lee.statement.expression;

import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.symbol.Signature;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;

public class Qualification extends Expression {

    public Qualification(Node current){
        super(current);
    }

    public Qualification(Node current, List<Expression> childNodes){
        super(current, childNodes);
    }

    public Qualification newChild(Node current){
        super.newChild(current);
        return this;
    }

    @Override
    public TypeTag getType() {
        return TypeTag.boolean_;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.qualification;
    }

    public Qualification toNegative(){
        boolean shouldWithParentheses = false;
        for(Expression child: this.childNodes){
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
        return new Qualification(StaticSymbol.PARENTHESES).newChild(toNegativeWithoutParentheses());
    }

    private Qualification toNegativeWithoutParentheses(){
        return new Qualification(StaticSymbol.NOT).newChild(this);
    }
}
