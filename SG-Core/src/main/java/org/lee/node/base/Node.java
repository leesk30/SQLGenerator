package org.lee.node.base;

import org.lee.node.NodeType;

import java.util.List;

public interface Node {
    String getString();
    NodeType getNodeType();
    default boolean isKindOf(NodeType nodeType){
        return getNodeType() == nodeType;
    }

    default boolean isKindsOf(NodeType[] nodeTypes){

    }

    default boolean isKindsOf(List<NodeType> nodeTypeList){

    }

    default boolean isSubKindOf(NodeType nodeType){
        NodeType self = getNodeType();
        if(nodeType)
    }
}
