package org.lee.symbol;

import org.lee.node.Node;
import org.lee.type.TypeTag;

import java.util.Collections;
import java.util.List;

public interface Signature extends Node {
    List<TypeTag> UNCONFIRMED_INPUT = Collections.singletonList(TypeTag.null_);
    List<TypeTag> getArgType();
    TypeTag getReturnType();
}
