package org.lee.entry.scalar;

import org.lee.node.NodeTag;
import org.lee.type.TypeDescriptor;
import org.lee.type.TypeTag;

public class Field extends ScalarWithinRelation {
    private final String fieldName;
    private final TypeTag fieldType;
    private final TypeDescriptor descriptor;

    public Field(String fieldName, TypeTag fieldType){
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.descriptor = TypeDescriptor.byInferPrecision(fieldType);
    }

    public Field(String fieldName, TypeDescriptor descriptor){
        this.fieldName = fieldName;
        this.fieldType = descriptor.getTag();
        this.descriptor = descriptor;
    }

    @Override
    public String getString() {
        return fieldName;
    }

    @Override
    public NodeTag getNodeTag() {
        return NodeTag.field;
    }

    @Override
    public TypeTag getType() {
        return fieldType;
    }

    public TypeDescriptor getDescriptor(){
        return descriptor;
    }

    public TypeDescriptor getTypeDescriptor() {
        return descriptor;
    }
}
