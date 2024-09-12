package org.lee.util;

import org.lee.common.MetaEntry;
import org.lee.entry.relation.Relation;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

public class FuzzUtil {
    public static final SecureRandom secureRandom = new SecureRandom();
    private static final char[] characters = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static boolean probability(int prob){
        return secureRandom.nextInt(100) < prob;
    }

    public static <T> T randomlyChooseFrom(List<T> list){
        if(list == null || list.isEmpty()){
            return null;
        }
        return list.get(secureRandom.nextInt(list.size()));
    }

    public static <T> T randomlyChooseFrom(T[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        return arr[secureRandom.nextInt(arr.length)];
    }

    public static int randomlyChooseFrom(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return arr[secureRandom.nextInt(arr.length)];
    }

    public static Relation getRandomRelationFromMetaEntry(){
        Object[] keys = MetaEntry.relationMap.keySet().toArray();
        String key = (String) randomlyChooseFrom(keys);
        return MetaEntry.relationMap.get(key);
    }

    public static Relation getRandomRelationFromMetaEntry(String limitationNamespace){
        if(limitationNamespace == null){
            return getRandomRelationFromMetaEntry();
        }
        return randomlyChooseFrom(MetaEntry.relationByNamespaceMap.get(limitationNamespace));
    }

    public static int randomIntFromRange(int beginInclusive, int endExclusive){
        if(beginInclusive == endExclusive) return beginInclusive;
        if(beginInclusive > endExclusive) throw new IndexOutOfBoundsException("The end index must be greater than begin index.");
        return secureRandom.nextInt(endExclusive - beginInclusive) + beginInclusive;
    }

    public static BigDecimal randomDecimalFromRange(int beginInclusive, int endExclusive){
        if(beginInclusive == endExclusive) return new BigDecimal(beginInclusive);
        if(beginInclusive > endExclusive) throw new IndexOutOfBoundsException("The end index must be greater than begin index.");
        String intPart = String.valueOf(secureRandom.nextInt(endExclusive - beginInclusive) + beginInclusive);
        String floatPart = new StringBuffer(String.valueOf(secureRandom.nextInt(100000000))).reverse().toString();
        return new BigDecimal(intPart + floatPart);
    }

    public static String getRandomName(String prefix){
        return prefix + "_" + UUID.randomUUID().toString().replace("-", "").substring(0, 6);
    }
}
