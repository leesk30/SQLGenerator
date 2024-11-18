package org.lee.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.lee.base.Node;
import org.lee.sql.entry.scalar.Scalar;
import org.lee.sql.type.TypeTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeUtils {

    public static String stringToLikePattern(String candidate){
        if(candidate.isEmpty()) return candidate;
        if(candidate.length() == 1) return RandomUtils.probability(50) ? "%" + candidate : candidate + "%";
        final int duplicatePatternProb = candidate.length() == 2 ? 0 : Math.min(10, candidate.length()/10 + 1);
        final int maxPattern = candidate.length() / 2;
        int times = 0;
        do{
            times++;
            int replacedBeginIndex = RandomUtils.randomIntFromRange(0, candidate.length());
            int replacedEndIndex = RandomUtils.randomIntFromRange(replacedBeginIndex, candidate.length());
            assert replacedEndIndex >= replacedBeginIndex;
            if(replacedBeginIndex == 0){
                return "%" + candidate.substring(1);
            }else if (replacedEndIndex == candidate.length() - 1){
                return candidate.substring(0, candidate.length() - 1) + "%";
            }
            candidate = candidate.substring(0, replacedBeginIndex) + "%" + candidate.substring(replacedEndIndex+1);
        }while (times < maxPattern && RandomUtils.probability(duplicatePatternProb));
        System.out.println("TotalTimes: " + times);
        return candidate;
    }

    public static <T extends Scalar> Map<TypeTag, List<T>> groupByType(final List<T> candidateList){
        Map<TypeTag, List<T>> result = new HashMap<>();
        return groupByType(candidateList, result);
    }

    public static <T extends Scalar> Map<TypeTag, List<T>> groupByType(final List<T> candidateList, final Map<TypeTag, List<T>> groupByType){
        candidateList.forEach(scalar -> {
            TypeTag type = scalar.getType();
            if(!groupByType.containsKey(type)){
                groupByType.put(type, new ArrayList<>());
            }
            groupByType.get(type).add(scalar);
        });
        return groupByType;
    }

    public static <T> void printList(List<T> list){
        String builder = "List("+list.size()+")[" +
                StringUtils.joinWith(", ", list.toArray()) +
                "]";
        System.out.println(builder);
    }

    public static <T extends Node> String formatNodeList(List<T> list){
        return "List("+list.size()+")[" +
                StringUtils.joinWith(", ", list.stream().map(Node::getString).toArray()) +
                "]";
    }

}
