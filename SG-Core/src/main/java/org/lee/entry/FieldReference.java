package org.lee.entry;

import org.lee.base.NodeTag;
import org.lee.entry.scalar.Field;
import org.lee.entry.scalar.Pseudo;
import org.lee.entry.scalar.Scalar;
import org.lee.entry.scalar.ScalarWithinRelation;
import org.lee.type.TypeTag;

public final class FieldReference implements Scalar {
    private final RangeTableReference parentRtr;
    private final ScalarWithinRelation scalar;

    public FieldReference(RangeTableReference parentRtr, Field field){
        this.parentRtr = parentRtr;
        this.scalar = field;
    }

    public FieldReference(RangeTableReference parentRtr, Pseudo pseudo){
        this.parentRtr = parentRtr;
        this.scalar = pseudo;
    }

//    public FieldReference(RangeTableReference parentRtr, int index){
//        this.parentRtr = parentRtr;
//        this.scalar = parentRtr.getFieldReferences().get(index);
//    }

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
        if(scalar instanceof Field){
            return (Field) scalar;
        }
        return new Field(getString(), getType());
    }

    public Scalar getReference(){
        return scalar;
    }

}
