package org.lee.base;

import org.lee.common.enumeration.NodeTag;

public interface VoidNode extends Node{

    @Override
    default NodeTag getNodeTag(){
        return NodeTag.__;
    }
}
