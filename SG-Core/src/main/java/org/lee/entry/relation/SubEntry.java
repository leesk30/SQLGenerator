package org.lee.entry.relation;

import org.lee.entry.NormalizedEntryNode;
import org.lee.entry.scalar.Field;
import org.lee.node.NodeTag;
import org.lee.statement.support.Projectable;

import java.util.List;
import java.util.Vector;

public abstract class SubEntry implements NormalizedEntryNode<Projectable>, RangeTableEntry {

    protected final Projectable projectable;
    protected final List<Field> fieldList;
    protected SubEntry(Projectable projectable){
        this.projectable = projectable;
        this.fieldList = new Vector<>(this.projectable.width());
        this.projectable.project().parallelStream().forEachOrdered(element -> fieldList.add(element.toField()));
    }
    @Override
    public List<Field> getFields() {
        return fieldList;
    }

    @Override
    public Projectable getRawNode() {
        return projectable;
    }
}
