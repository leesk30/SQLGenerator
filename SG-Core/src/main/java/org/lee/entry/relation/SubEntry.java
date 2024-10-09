package org.lee.entry.relation;

import org.lee.entry.NormalizedEntryWrapper;
import org.lee.entry.scalar.Field;
import org.lee.statement.support.Projectable;

import java.util.ArrayList;
import java.util.List;

public abstract class SubEntry implements NormalizedEntryWrapper<Projectable>, RangeTableEntry {

    protected final Projectable projectable;
    protected final List<Field> fieldList;
    protected SubEntry(Projectable projectable){
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
