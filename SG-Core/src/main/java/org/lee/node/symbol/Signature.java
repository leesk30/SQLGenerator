package org.lee.node.symbol;

import org.lee.type.TypeTag;
import org.lee.type.base.SGType;

import java.util.List;

public interface Signature {
    List<SGType> getArgType();
    SGType getReturnType();
    default TypeTag getReturnTypeTag(){
        return getReturnType().getTypeTag();
    }
}
