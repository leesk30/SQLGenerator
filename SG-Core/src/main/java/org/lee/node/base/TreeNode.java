package org.lee.node.base;

import org.lee.node.base.Node;

import java.util.List;

public interface TreeNode extends Node {
    default List<Node> walk(){
        // todo: implement recursion walk
        return null;
    }
}
