package org.lee.statement.entry.scalar;

import org.lee.statement.node.NodeTag;
import org.lee.type.TypeTag;

public class Field implements Scalar {
    private final String fieldName;
    private final TypeTag fieldType;

    public Field(String fieldName, TypeTag fieldType){
        this.fieldName = fieldName;
        this.fieldType = fieldType;
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
}
