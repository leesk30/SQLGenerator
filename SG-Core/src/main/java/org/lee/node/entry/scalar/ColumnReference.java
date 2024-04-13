package org.lee.node.entry.scalar;

import org.lee.node.NodeTag;
import org.lee.node.entry.relation.RangeTableEntry;
import org.lee.node.entry.relation.RangeTableReference;
import org.lee.type.base.SGType;

public class ColumnReference implements Scalar {
    private final RangeTableReference parentRtr;
    private final Scalar scalar;

    public <T extends Scalar> ColumnReference(RangeTableReference parentRtr, T scalar){
        this.parentRtr = parentRtr;
        this.scalar = scalar;
    }

    public ColumnReference(RangeTableReference parentRtr, int index){
        this.parentRtr = parentRtr;
        this.scalar = parentRtr.getFields().get(index);
    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public NodeTag getNodeTag() {
        return null;
    }

    @Override
    public SGType getType() {
        return null;
    }
}
