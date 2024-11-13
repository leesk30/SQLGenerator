package org.lee.sql.entry.relation;

import org.lee.sql.entry.Normalized;
import org.lee.sql.entry.scalar.Field;
import org.lee.sql.support.Projectable;

import java.util.ArrayList;
import java.util.List;

public abstract class SubRTE implements Normalized<Projectable>, RangeTableEntry {

    protected final Projectable projectable;
    protected final List<Field> fieldList;
    protected SubRTE(Projectable projectable){
        this.projectable = projectable;
        this.fieldList = new ArrayList<>(this.projectable.width());
        this.projectable.project().forEach(element -> fieldList.add(element.toField()));
    }
    @Override
    public List<Field> getFields() {
        return fieldList;
    }

    @Override
    public Projectable getWrapped() {
        return projectable;
    }
}
