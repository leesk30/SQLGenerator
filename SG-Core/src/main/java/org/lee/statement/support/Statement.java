package org.lee.statement.support;

import org.lee.fuzzer.Fuzzer;
import org.lee.node.Node;
import org.lee.node.TreeNode;

public interface Statement<T extends Node> extends TreeNode<T>, SupportRuntimeConfiguration, Fuzzer {
    Statement<?> getParent();
}
