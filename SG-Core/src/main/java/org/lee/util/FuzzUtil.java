package org.lee.util;

import org.lee.common.MetaEntry;
import org.lee.statement.entry.relation.Relation;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;

public class FuzzUtil {
    public static final SecureRandom secureRandom = new SecureRandom();

    public static boolean probability(int prob){
        return secureRandom.nextInt(100) <= prob;
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
}
