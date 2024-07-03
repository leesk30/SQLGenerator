package org.lee.statement.entry.relation;

import org.lee.statement.node.Node;
import org.lee.statement.entry.scalar.Scalar;

import java.util.List;

public interface RangeTableEntry extends Node {
    List<? extends Scalar> getFields();
    String getName();

}
