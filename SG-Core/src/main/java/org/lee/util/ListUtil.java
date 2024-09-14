package org.lee.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListUtil {

    public static <T> List<T> copyList(final List<T> source){
        if(source == null || source.isEmpty()){
            return Collections.emptyList();
        }
        final List<T> dest = new Vector<>(source.size());
        source.parallelStream().forEach(dest::add);
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
