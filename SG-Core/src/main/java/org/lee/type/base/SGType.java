package org.lee.type.base;

import org.lee.type.TypeTag;
import org.lee.type.precision.TypePrecision;

public interface SGType {
    TypeTag getTypeTag();
    TypePrecision getTypePrecision();
    boolean isUDT();
}
