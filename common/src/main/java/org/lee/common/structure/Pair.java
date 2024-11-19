package org.lee.common.structure;


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
}
