package org.lee.entry;

import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Scalar;
import org.lee.node.NodeTag;
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
        if(parentRtr != null){
            return String.format("%s.%s", parentRtr.getName(), scalar.getString());
        }
        return scalar.getString();
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.fieldReference;
    }

    @Override
    public TypeTag getType() {
        return this.scalar.getType();
    }

    public Field toField(){
        return new Field(getString(), getType());
    }


}
