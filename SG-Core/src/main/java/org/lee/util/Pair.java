package org.lee.util;


import org.lee.entry.literal.Literal;

import java.util.Optional;

public class Pair<T1, T2> {
    private final Optional<T1> t1;
    private final Optional<T2> t2;

    public Pair(T1 t1, T2 t2) {
        this.t1 = Optional.of(t1);
        this.t2 = Optional.of(t2);
    }

    public Optional<T1> getFirst() {
        return t1;
    }

    public Optional<T2> getSecond() {
        return t2;
    }

    public T1 getFirstOrElse(T1 orElse){
        return t1.orElse(orElse);
    }

    public T2 getSecondOrElse(T2 orElse){
        return t2.orElse(orElse);
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
