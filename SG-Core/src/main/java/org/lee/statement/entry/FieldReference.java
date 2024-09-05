package org.lee.statement.entry;

import org.lee.statement.entry.scalar.Scalar;
import org.lee.statement.node.NodeTag;
import org.lee.statement.entry.RangeTableReference;
import org.lee.type.TypeTag;

public final class FieldReference implements Scalar {
    private final RangeTableReference parentRtr;
    private final Scalar scalar;

    public <T extends Scalar> FieldReference(RangeTableReference parentRtr, T scalar){
        this.parentRtr = parentRtr;
        this.scalar = scalar;
    }

    public FieldReference(RangeTableReference parentRtr, int index){
        this.parentRtr = parentRtr;
        this.scalar = parentRtr.getFieldReferences().get(index);
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
    public TypeTag getType() {
        return null;
    }
}
