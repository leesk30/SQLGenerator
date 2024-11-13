package org.lee.sql.expression;

import org.lee.base.Node;
import org.lee.base.TreeNode;
import org.lee.common.Assertion;
import org.lee.common.structure.Pair;
import org.lee.entry.FieldReference;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Pseudo;
import org.lee.entry.scalar.Scalar;
import org.lee.sql.symbol.Aggregation;
import org.lee.sql.symbol.basic.Symbol;
import org.lee.sql.symbol.Window;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Expression System is almost totally abstractive.
 * */
public interface IExpression<E extends IExpression<E>> extends Scalar, TreeNode<IExpression<Expression>> {
    // IE is your self.
    // E is your child.
    // E is subtype of IE
    // IExpression<Expression> represent all kinds of the expression
    boolean isTerminateNode();

    List<IExpression<Expression>> getChildNodes();

    Node getCurrent();

    List<E> getLeafs();

    E newChild(Node current);

    IExpression<E> toWithParentheses();

    default boolean isCurrentAggregation(){
        return getCurrent() instanceof Aggregation;
    }

    default boolean isCurrentPseudo(){
        return getCurrent() instanceof Pseudo;
    }

    default boolean isCurrentWindow(){
        return getCurrent() instanceof Window;
    }


    default boolean isLeaf(){
        return getChildNodes().isEmpty();
    }

    default boolean isCurrentComplete(){
        return isTerminateNode() || ((Symbol) getCurrent()).argsNum() == getChildNodes().size();
    }

    default boolean isComplete(){
        if(!isCurrentComplete()){
            return false;
        }
        if(isLeaf()){
            return true;
        }
        for(IExpression<Expression> child: getChildNodes()){
            if(!child.isComplete()){
                return false;
            }
        }
        return true;
    }


    default boolean isIncludingAggregation(){
        if (!isTerminateNode() && isCurrentAggregation()){
            return true;
        }
        for(IExpression<Expression> child: getChildNodes()){
            if(child.isIncludingAggregation()){
                return true;
            }
        }
        return false;
    }

    default boolean isIncludingPseudo(){
        if(isLeaf()){
            return isCurrentPseudo();
        }
        for(IExpression<Expression> child: getChildNodes()){
            if(child.isIncludingPseudo()){
                return true;
            }
        }
        return false;
    }

    default boolean isIncludingWindow(){
        if (isCurrentWindow()){
            return true;
        }

        for(IExpression<Expression> child: getChildNodes()){
            if(child.isIncludingWindow()){
                return true;
            }
        }
        return false;
    }

    default int getTotalDegree(){
        if(isLeaf()){
            return 1;
        }
        return getChildNodes()
                .stream()
                .map(IExpression::getTotalDegree)
                .max(Integer::compare)
                .orElseThrow(Assertion.IMPOSSIBLE) + 1;
    }

    default List<IExpression<Expression>> midTraverseExpression(){
        final List<IExpression<Expression>> expressionList = new ArrayList<>(getTotalDegree() * getChildNodes().size());
        final LinkedList<IExpression<Expression>> fifo = new LinkedList<>(this.getChildNodes());
        while (!fifo.isEmpty()){
            final IExpression<Expression> current = fifo.removeFirst();
            fifo.addAll(current.getChildNodes());
            expressionList.add(current);
        }
        return expressionList;
    }

    default Stream<IExpression<Expression>> walk(){
        return midTraverseExpression().stream();
    }

    default List<TypeTag> getLeafRequired(){
        if(isLeaf()){
            if(!isTerminateNode()){
                return new ArrayList<>(((Symbol) getCurrent()).getArgumentsTypes());
            }else {
                return Collections.emptyList();
            }
        }
        final List<TypeTag> types = new ArrayList<>();
        getChildNodes().forEach(child -> types.addAll(child.getLeafRequired()));
        return types;
    }

    default List<FieldReference> extractField(){
        if(isLeaf() && isTerminateNode() && getCurrent() instanceof FieldReference){
            return Collections.singletonList((FieldReference) getCurrent());
        }

        final List<E> leaf = this.getLeafs();
        return leaf.stream()
                .map(E::getCurrent)
                .filter(each -> each instanceof FieldReference)
                .filter(each -> {
                    final Scalar referenced = ((FieldReference) each).getReference();
                    return referenced instanceof Field || referenced instanceof Pseudo;
                })
                .map(each -> (FieldReference) each)
                .collect(Collectors.toList());
    }

    default Pair<List<FieldReference>, List<FieldReference>> extractAggregate(){
        final List<FieldReference> inAggregator = new ArrayList<>();
        final List<FieldReference> notInAggregator = new ArrayList<>();
        final Pair<List<FieldReference>, List<FieldReference>> pair = new Pair<>(inAggregator, notInAggregator);

        if(isCurrentAggregation()){
            inAggregator.addAll(extractField());
            return pair;
        }

        if(!isIncludingAggregation()){
            notInAggregator.addAll(extractField());
            return pair;
        }

        for(IExpression<Expression> expression: getChildNodes()){
            if(expression.isCurrentAggregation()){
                inAggregator.addAll(expression.extractField());
            }else if(expression.isIncludingAggregation()) {
                // recursive find aggregate
                final Pair<List<FieldReference>, List<FieldReference>> subpair = expression.extractAggregate();
                inAggregator.addAll(subpair.getFirstOrElse(Collections.emptyList()));
                notInAggregator.addAll(subpair.getSecondOrElse(Collections.emptyList()));
            }else {
                notInAggregator.addAll(expression.extractField());
            }
        }
        return pair;
    }

    default void copyChildrenFrom(E expression){
        this.getChildNodes().addAll(expression.getChildNodes());
    }

    @Override
    default TypeTag getType() {
        if(isTerminateNode()){
            return ((Scalar) getCurrent()).getType();
        }
        return ((Symbol) getCurrent()).getReturnType();
    }

    @Override
    default String getString() {
        if(isTerminateNode()){
            Assertion.requiredTrue(getChildNodes().isEmpty());
            return getCurrent().getString();
        }
        return String.format(getCurrent().getString(), getChildNodes().toArray());
    }

}
