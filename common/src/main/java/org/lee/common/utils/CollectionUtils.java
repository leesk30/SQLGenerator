package org.lee.common.utils;

import org.lee.common.Assertion;

import java.util.*;

public class CollectionUtils {
    public static <T> Set<T> union(Set<T> lhs, Set<T> rhs){
        Assertion.requiredNonNull(lhs);
        Assertion.requiredNonNull(rhs);
        Set<T> result = new HashSet<>(lhs);
        result.addAll(rhs);
        return result;
    }

    public static <T> Set<T> intersect(Set<T> lhs, Set<T> rhs){
        Assertion.requiredNonNull(lhs);
        Assertion.requiredNonNull(rhs);
        Set<T> result = new HashSet<>(lhs);
        result.retainAll(rhs);
        return result;
    }

    public static <T> Set<T> diff(Set<T> diff, Set<T> rhs){
        Assertion.requiredNonNull(diff);
        Assertion.requiredNonNull(rhs);
        Set<T> result = new HashSet<>(diff);
        result.removeAll(rhs);
        return result;
    }

    public static <T> List<T> copyList(final List<T> source){
        if(source == null || source.isEmpty()){
            // cannot be emptyList here
            return new ArrayList<>();
        }
        final List<T> dest = new ArrayList<>(source.size());
        dest.addAll(source);
        return dest;
    }

    public static <T> List<T> copyListShuffle(final List<T> source){
        List<T> dest = copyList(source);
        Collections.shuffle(dest);
        return dest;
    }

    public static <T> List<T> copyFrozenList(final List<T> source){
        return Collections.unmodifiableList(source);
    }
}
