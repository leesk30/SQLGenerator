package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.base.SGType;
import org.lee.type.precision.TypePrecision;

public class SGBoolean extends AbstractBasicDataType {

    protected SGBoolean(TypeTag typeTag, TypePrecision precision) {
        super(typeTag, precision);
    }

    @Override
    public TypeTag getTypeTag() {
        return null;
    }

    @Override
    public TypePrecision getTypePrecision() {
        return null;
    }
}
