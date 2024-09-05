package org.lee.statement.node;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public interface Node {
    String getString();
    NodeTag getNodeTag();

    default <T extends Node> String nodeArrayToString(final String separator, final List<T> nodeList){
        return StringUtils.joinWith(separator, nodeList.stream().map(Node::getString).iterator());
    }

    default <T extends Node> String nodeArrayToString(final List<T> nodeList) {
        return nodeArrayToString(", ", nodeList);
    }

    default <T extends Node> String nodeArrayToString(final String separator, final T[] nodeArr){
        return StringUtils.joinWith(separator, Arrays.stream(nodeArr).map(Node::getString).iterator());
    }

    default <T extends Node> String nodeArrayToString(final T[] nodeArr){
        return nodeArrayToString(", ", nodeArr);
    }
}
