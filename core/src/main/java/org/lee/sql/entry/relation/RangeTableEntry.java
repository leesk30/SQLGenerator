package org.lee.sql.entry.relation;

import org.lee.base.Node;
import org.lee.context.SQLGeneratorContext;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.statement.Projectable;
import org.lee.sql.statement.select.SelectDynamicStatement;

import java.util.List;

public interface RangeTableEntry extends Node {
    List<Field> getFields();
    String getName();

    default int capacity(){
        return getFields().size();
    }

    default RangeTableEntry toShelledSubqueryEntry(SQLGeneratorContext context){
        Projectable projectable = SelectDynamicStatement.fromEntry(context, this);
        return new SubqueryRelation(projectable);
    }

}
