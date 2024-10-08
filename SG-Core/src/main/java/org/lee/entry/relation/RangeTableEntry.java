package org.lee.entry.relation;

import org.lee.base.Node;
import org.lee.entry.scalar.Field;

import java.util.List;

public interface RangeTableEntry extends Node {
    List<Field> getFields();
    String getName();

}
