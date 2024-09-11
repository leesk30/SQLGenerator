package org.lee.node;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Node {
    String getString();
    NodeTag getNodeTag();

    default <T extends Node> String nodeArrayToString(final String separator, final Stream<T> nodeStream){
        final String reducerSeparator = "%s" + separator + "%s";
        return nodeStream.parallel()
                .map(Node::getString)
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
