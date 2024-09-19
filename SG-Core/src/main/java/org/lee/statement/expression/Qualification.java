package org.lee.statement.expression;

import org.lee.entry.scalar.Scalar;
import org.lee.node.Node;
import org.lee.node.NodeTag;
import org.lee.symbol.Parentheses;
import org.lee.symbol.PredicateCombiner;
import org.lee.symbol.Signature;
import org.lee.symbol.StaticSymbol;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public Qualification toWithParenthesesExpression(){
        Parentheses parentheses = isTerminateNode? ((Scalar) current).getType().getParenthesesSymbol() : ((Signature)current).toParentheses();
        Qualification newer = new Qualification(parentheses) ;
        newer.childNodes.addAll(childNodes);
        return newer;
    }

    private Qualification toNegativeWithParentheses(){
        return toNegativeWithoutParentheses().toWithParenthesesExpression();
    }

    private Qualification toNegativeWithoutParentheses(){
        return new Qualification(PredicateCombiner.NOT).newChild(this);
    }

    public Qualification and(final Qualification rhs){
        return new Qualification(PredicateCombiner.AND).newChild(this).newChild(rhs);
    }
}
