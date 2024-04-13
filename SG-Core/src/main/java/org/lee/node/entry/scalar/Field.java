package org.lee.node.entry.scalar;

import org.lee.node.NodeTag;
import org.lee.type.base.SGType;

public class Field implements Scalar {
    private final String fieldName;
    private final SGType fieldType;

    public Field(String fieldName, SGType fieldType){
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
    public SGType getType() {
        return fieldType;
    }
}
