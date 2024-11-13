package org.lee.sql.entry.relation;

import org.lee.base.Node;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.statement.select.SelectDynamicStatement;
import org.lee.sql.support.Projectable;

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
