package org.lee.type.base;

import org.lee.type.TypeTag;
import org.lee.type.precision.TypePrecision;

/**
 * basic data type
 * */
public abstract class AbstractBasicDataType extends AbstractDataType {
    protected AbstractBasicDataType(TypeTag typeTag, TypePrecision precision){
        super(typeTag, precision);
    }

    @Override
    public boolean isUDT() {
        return false;
    }
}
