package org.lee.testutils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Printer {
    public static <T> void printList(List<T> list){
        String builder = "List[" +
                StringUtils.joinWith(", ", list.toArray()) +
                "]";
        System.out.println(builder);
    }
}
