package org.lee.node;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public interface TreeNode<T extends Node> extends Node {
    List<? extends Node> getChildNodes();
    Stream<T> walk();
}
