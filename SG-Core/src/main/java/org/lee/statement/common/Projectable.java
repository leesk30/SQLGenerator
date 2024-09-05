package org.lee.statement.common;

import org.lee.statement.entry.relation.RangeTableEntry;
import org.lee.statement.entry.scalar.Field;
import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.node.Node;

import java.util.List;

public interface Projectable extends Node {
    List<Field> project();
    RangeTableEntry toRelation();

    default int width(){
        return project().size();
    }
}
