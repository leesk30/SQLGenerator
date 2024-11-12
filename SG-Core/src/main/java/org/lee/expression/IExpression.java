package org.lee.expression;

import org.lee.base.Node;
import org.lee.base.TreeNode;
import org.lee.common.Assertion;
import org.lee.entry.scalar.Scalar;
import org.lee.type.TypeTag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public interface IExpression<E extends IExpression<E>> extends Scalar, TreeNode<IExpression<E>> {

    boolean isCurrentAggregation();

    boolean isCurrentPseudo();

    boolean isCurrentWindow();

    boolean isCurrentComplete();

    boolean isTerminateNode();

    List<E> getChildNodes();

    Node getCurrent();

    List<E> getLeafs();

    List<TypeTag> getLeafRequired();

    default boolean isLeaf(){
        return getChildNodes().isEmpty();
    }

    default boolean isComplete(){
        if(!isCurrentComplete()){
            return false;
        }
        if(isLeaf()){
            return true;
        }
        for(E child: getChildNodes()){
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
        for(E child: getChildNodes()){
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
        for(E child: getChildNodes()){
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

        for(E child: getChildNodes()){
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

    default List<IExpression<E>> midTraverseExpression(){
        final List<IExpression<E>> expressionList = new ArrayList<>(getTotalDegree() * getChildNodes().size());
        final LinkedList<IExpression<E>> fifo = new LinkedList<>();
        fifo.add(this);
        while (!fifo.isEmpty()){
            final IExpression<E> current = fifo.removeFirst();
            fifo.addAll(current.getChildNodes());
            expressionList.add(current);
        }
        return expressionList;
    }

    default Stream<IExpression<E>> walk(){
        return midTraverseExpression().stream();
    }
}
