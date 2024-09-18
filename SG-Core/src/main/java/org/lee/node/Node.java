package org.lee.node;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Node {
    String ENDING = ";";
    String LP = "(";
    String RP = ")";
    String SPACE = " ";
    String COMMA = ", ";
    String SELECT = "SELECT";
    String INSERT = "INSERT";
    String VALUES = "VALUES";
    String DELETE = "DELETE";
    String UPDATE = "UPDATE";
    String MERGE = "MERGE";
    String INTO = "INTO";
    String FROM = "FROM";
    String WITH = "WITH";
    String WHERE = "WHERE";
    String ORDER = "ORDER";
    String GROUP = "GROUP";
    String BY = "BY";
    String PIVOT = "PIVOT";
    String UNPIVOT = "UNPIVOT";
    String LIMIT = "LIMIT";
    String OFFSET = "OFFSET";

    String getString();
    NodeTag getNodeTag();

    default <T extends Node> String nodeArrayToString(final String separator, final Stream<T> nodeStream){
        final String reducerSeparator = "%s" + separator + "%s";
        return nodeStream.parallel()
                .map(Node::getString)
                .filter(s -> !StringUtils.isEmpty(s) && !StringUtils.isBlank(s))
                .reduce((s1, s2) -> String.format(reducerSeparator, s1, s2))
                .orElse("null");
    }

    default <T extends Node> String nodeArrayToString(final Stream<T> nodeStream){
        return nodeArrayToString(", ", nodeStream);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final List<T> nodeList){
        return nodeArrayToString(separator, nodeList.stream());
    }

    default <T extends Node> String nodeArrayToString(final List<T> nodeList) {
        return nodeArrayToString(", ", nodeList);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final T[] nodeArr){
        return nodeArrayToString(separator, Arrays.stream(nodeArr));
    }

    default <T extends Node> String nodeArrayToString(final T[] nodeArr){
        return nodeArrayToString(", ", nodeArr);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final Iterator<T> nodeIterator){
        return nodeArrayToString(separator, StreamSupport.stream(Spliterators.spliteratorUnknownSize(nodeIterator, 0), false));
    }

    default <T extends Node> String nodeArrayToString(final Iterator<T> nodeIterator){
        return nodeArrayToString(", ", nodeIterator);
    }
}
