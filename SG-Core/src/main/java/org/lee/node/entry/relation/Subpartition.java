package org.lee.node.entry.relation;

public class Subpartition extends Partition{
    private final Partition parentPartition;
    public Subpartition(Partition partition) {
        super((Relation) partition.getParent());
        parentPartition = partition;
    }

    @Override
    public boolean isSubPartition(){
        return true;
    }

    @Override
    public RangeTableEntry getParent(){
        return parentPartition;
    }
}
