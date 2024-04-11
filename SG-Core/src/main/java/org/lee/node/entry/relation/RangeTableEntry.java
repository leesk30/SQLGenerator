package org.lee.node.entry.relation;

import org.lee.node.base.Node;
import org.lee.node.entry.scalar.Field;

import java.util.List;

public interface RangeTableEntry extends Node {
    List<Field> getFields();

}
