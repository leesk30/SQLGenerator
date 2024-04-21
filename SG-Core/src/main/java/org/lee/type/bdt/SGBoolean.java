package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.precision.NonPrecision;
public class SGBoolean extends AbstractBasicDataType {
    public SGBoolean() {
        super(TypeTag.boolean_, new NonPrecision());
    }

}
