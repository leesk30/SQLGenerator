package org.lee.statement.entry.normalized;

import org.lee.statement.node.Node;
import org.lee.statement.entry.EntryNode;

public interface NormalizedEntryNode<N extends Node> extends EntryNode {
    N getOriginalNode();
}
