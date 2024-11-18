package org.lee.common.debug;

import org.apache.commons.lang3.StringUtils;
import org.lee.base.Node;

import java.util.List;

public class Printer {
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
