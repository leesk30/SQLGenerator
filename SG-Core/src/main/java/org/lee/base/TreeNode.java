package org.lee.base;

import java.util.List;
import java.util.stream.Stream;

public interface TreeNode<T extends Node> extends Node {
    List<? extends Node> getChildNodes();
    Stream<? extends T> walk();
}
