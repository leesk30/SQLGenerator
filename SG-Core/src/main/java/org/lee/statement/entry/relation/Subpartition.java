package org.lee.statement.entry.relation;

public class Subpartition extends Partition{
    private final Partition parentPartition;
    public Subpartition(String subpartitionName, Partition partition) {
        super(subpartitionName, (Relation) partition.getParent());
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
