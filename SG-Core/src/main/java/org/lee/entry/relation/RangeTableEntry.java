package org.lee.entry.relation;

import org.lee.base.Node;
import org.lee.entry.scalar.Field;
import org.lee.statement.select.SelectDynamicStatement;
import org.lee.statement.support.Projectable;

import java.util.List;

public interface RangeTableEntry extends Node {
    List<Field> getFields();
    String getName();

    default int capacity(){
        return getFields().size();
    }

    default RangeTableEntry toShelledSubqueryEntry(){
        Projectable projectable = SelectDynamicStatement.fromEntry(this);
        return new SubqueryRelation(projectable);
    }

}
