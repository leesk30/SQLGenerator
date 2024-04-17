package org.lee.node.entry.normalized;

import org.lee.node.base.Node;
import org.lee.node.entry.EntryNode;

public interface NormalizedEntryNode<N extends Node> extends EntryNode {
    N getOriginalNode();
}
