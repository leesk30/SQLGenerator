package org.lee.entry.relation;

import org.lee.entry.scalar.Field;
import org.lee.node.Node;

import java.util.List;

public interface RangeTableEntry extends Node {
    List<Field> getFields();
    String getName();

}