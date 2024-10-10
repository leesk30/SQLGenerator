package org.lee.statement.support;

import org.lee.base.Fuzzer;
import org.lee.base.Node;
import org.lee.base.TreeNode;

import java.util.UUID;

public interface Statement<T extends Node> extends TreeNode<T>,
        SupportRuntimeConfiguration, Fuzzer, Logging {
    Statement<?> getParent();
    UUID getUUID();
}
