package org.lee.base;

public interface VoidNode extends Node{

    @Override
    default NodeTag getNodeTag(){
        return NodeTag.__;
    }
}
