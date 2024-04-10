package org.lee.type.base;

import org.lee.type.TypeTag;
import org.lee.type.precision.NonPrecision;

public abstract class AbstractUserDefinedDataType extends AbstractDataType{
    protected AbstractUserDefinedDataType(TypeTag typeTag){
        super(typeTag, new NonPrecision());
    }

    @Override
    public boolean isUDT() {
        return true;
    }
}
