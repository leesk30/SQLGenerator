package org.lee.type.base;

import org.lee.type.TypeTag;
import org.lee.type.precision.TypePrecision;

/**
 * Abstract data type
 * */
public abstract class AbstractDataType implements SGType {
    protected TypeTag typeTag;
    protected TypePrecision typePrecision;

    protected AbstractDataType(TypeTag typeTag, TypePrecision precision){
        this.typeTag = typeTag;
        this.typePrecision = precision;
    }

    @Override
    public TypePrecision getTypePrecision() {
        return typePrecision;
    }

    @Override
    public TypeTag getTypeTag() {
        return typeTag;
    }
}
