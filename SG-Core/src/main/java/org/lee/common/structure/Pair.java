package org.lee.common.structure;


import org.lee.type.literal.Literal;

import java.util.Collection;
import java.util.Iterator;

public class Pair<T1, T2> {
    private final T1 t1;
    private final T2 t2;

    public Pair(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public static <E> Pair<E, E> fromCollection(Collection<E> collection){
        Iterator<E> it = collection.iterator();
        // todo
        return new Pair<>(it.next(), it.next());
    }

    public T1 getFirst() {
        return t1;
    }

    public T2 getSecond() {
        return t2;
    }

    public T1 getFirstOrElse(T1 orElse){
        if(t1 == null){
            return orElse;
        }
        return t1;
    }

    public T2 getSecondOrElse(T2 orElse){
        if(t2 == null){
            return orElse;
        }
        return t2;
    }

    public static <T> Pair<Literal<T>, Literal<T>> OrderedPair(Literal<T> v1, Literal<T> v2){
        if(!(v1.getType().isComparable() && v1.getLiteral() instanceof Comparable)){
            return null;
        }
        Comparable<T> comparable = (Comparable<T>) v1.getLiteral();
        final int result = comparable.compareTo(v2.getLiteral());
        if(result == 0){
            return null;
        }
        return result > 0 ? new Pair<>(v2, v1):new Pair<>(v1, v2);
    }
}
