package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.precision.NonPrecision;

public class SGDate extends AbstractBasicDataType {
    protected SGDate() {
        super(TypeTag.date, new NonPrecision());
    }
}
