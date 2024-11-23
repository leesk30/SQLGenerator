package org.lee.sql.expression;

import org.lee.base.Node;
import org.lee.common.enumeration.NodeTag;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.literal.Literal;
import org.lee.sql.literal.LiteralInt;
import org.lee.sql.symbol.Comparator;
import org.lee.sql.symbol.Parentheses;
import org.lee.sql.symbol.Symbol;
import org.lee.sql.symbol.common.PredicateCombiner;
import org.lee.sql.type.TypeTag;

import java.util.List;

public class Qualification extends Expression {

    private static final LiteralInt v = new LiteralInt(1);
    public static final Qualification ALWAYS_TRUE = new Qualification(Comparator.EQ).newChild(v).newChild(v);
    public static final Qualification ALWAYS_FALSE = new Qualification(Comparator.NOT_EQ).newChild(v).newChild(v);

    public Qualification(Node current){
        super(current);
    }

    public Qualification(Node current, List<IExpression<Expression>> childNodes){
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
        for(IExpression<Expression> child: this.childNodes){
            if(child instanceof Qualification){
                shouldWithParentheses = true;
                break;
            }
        }

        if(shouldWithParentheses){
            return not().toWithParentheses();
        }else {
            return not();
        }
    }

    @Override
    public Qualification toWithParentheses(){
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
    public Qualification toCompleteQualification(){
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
