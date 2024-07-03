package org.lee.symbol;

import org.lee.statement.node.Node;
import org.lee.type.TypeTag;

import java.util.List;

public interface Signature extends Node {
    List<TypeTag> getArgType();
    TypeTag getReturnType();
}
