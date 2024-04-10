package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.precision.NonPrecision;
import org.lee.type.precision.TypePrecision;

public class SGDate extends AbstractBasicDataType {
    protected SGDate() {
        super(TypeTag.DATE, new NonPrecision());
    }
}
