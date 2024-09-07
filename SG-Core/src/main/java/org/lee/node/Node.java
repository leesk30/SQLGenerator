package org.lee.node;

import java.util.*;
import java.util.stream.StreamSupport;

public interface Node {
    String getString();
    NodeTag getNodeTag();

    default <T extends Node> String nodeArrayToString(final String separator, final List<T> nodeList){
        final String reducerSeparator = "%s" + separator + "%s";
        final Optional<String> optional = nodeList.parallelStream().map(Node::getString).reduce((s1, s2) -> String.format(reducerSeparator, s1, s2));
        return optional.orElse("");
    }

    default <T extends Node> String nodeArrayToString(final List<T> nodeList) {
        return nodeArrayToString(", ", nodeList);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final T[] nodeArr){
        final String reducerSeparator = "%s" + separator + "%s";
        final Optional<String> optional = Arrays.stream(nodeArr).parallel().map(Node::getString).reduce((s1, s2) -> String.format(reducerSeparator, s1, s2));
        return optional.orElse("");
    }

    default <T extends Node> String nodeArrayToString(final T[] nodeArr){
        return nodeArrayToString(", ", nodeArr);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final Iterator<T> nodeIterator){
        final String reducerSeparator = "%s" + separator + "%s";
        final Optional<String> optional = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(nodeIterator, 0), false)
                .map(Node::getString).reduce((s1, s2) -> String.format(reducerSeparator, s1, s2));
        return optional.orElse("");
    }

    default <T extends Node> String nodeArrayToString(final Iterator<T> nodeIterator){
        return nodeArrayToString(", ", nodeIterator);
    }
}
