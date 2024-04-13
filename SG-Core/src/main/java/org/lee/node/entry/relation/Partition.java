package org.lee.node.entry.relation;

import org.lee.node.NodeTag;
import org.lee.node.entry.scalar.Field;
import org.lee.node.entry.scalar.Scalar;

import java.util.List;

public class Partition implements RangeTableEntry {
    protected final Relation parent;

    public Partition(Relation parent){
        this.parent = parent;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    public boolean isSubPartition(){
        return false;
    }

    @Override
    public List<Field> getFields() {
        return parent.getFields();
    }

    public RangeTableEntry getParent() {
        return parent;
    }
}
