package org.lee.node.entry.relation;

import org.lee.node.NodeTag;
import org.lee.node.entry.scalar.Field;

import java.util.List;

public class Partition<P extends RangeTableEntry> implements RangeTableEntry {
    private P parent;

    public Partition(P parent){
        this.isSubPartition = parent instanceof Partition;
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeType() {
        return null;
    }

    boolean isSubPartition(){
        return parent instanceof Partition;
    }

    @Override
    public List<Field> getFields() {
        return parent.getFields();
    }
}
