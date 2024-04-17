package org.lee.symbol;

import org.lee.node.base.Node;
import org.lee.type.TypeTag;
import org.lee.type.base.SGType;

import java.util.List;

public interface Signature extends Node {
    List<List<SGType>> getArgType();
    boolean hasOverwrite();
    SGType getReturnType();
    default TypeTag getReturnTypeTag(){
        return getReturnType().getTypeTag();
    }
}
