package org.lee.sql.entry.relation;

import org.lee.common.enumeration.NodeTag;
import org.lee.sql.entry.scalar.Field;

import java.util.List;

public class Partition implements RangeTableEntry {
    protected final Relation parent;
    protected final String partitionName;

    public Partition(String partitionName, Relation parent){
        this.parent = parent;
        this.partitionName = partitionName;
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

    @Override
    public String getName() {
        return partitionName;
    }

    public RangeTableEntry getParent() {
        return parent;
    }
}
