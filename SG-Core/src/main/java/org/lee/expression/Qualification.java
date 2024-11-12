package org.lee.expression;

import org.lee.base.Node;
import org.lee.base.NodeTag;
import org.lee.entry.scalar.Scalar;
import org.lee.symbol.Parentheses;
import org.lee.symbol.PredicateCombiner;
import org.lee.symbol.Symbol;
import org.lee.type.TypeTag;

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
            return not().toWithParenthesesExpression();
        }else {
            return not();
        }
    }

    @Override
    public Qualification toWithParenthesesExpression(){
        Parentheses parentheses = isTerminateNode? ((Scalar) current).getType().getParenthesesSymbol() : ((Symbol)current).toParentheses();
        Qualification newer = new Qualification(parentheses) ;
        newer.childNodes.addAll(childNodes);
        return newer;
    }

    public Qualification and(final Qualification rhs){
        return new Qualification(PredicateCombiner.AND).newChild(this).newChild(rhs);
    }

    public Qualification or(final  Qualification rhs){
        return new Qualification(PredicateCombiner.OR).newChild(this).newChild(rhs);
    }

    public Qualification not(){
        return new Qualification(PredicateCombiner.NOT).newChild(this);
    }

    @Override
    public Qualification toQualification(){
        return this;
    }

    @Override
    public boolean isCurrentAggregation() {
        return false;
    }

    @Override
    public boolean isCurrentWindow() {
        return false;
    }
}
