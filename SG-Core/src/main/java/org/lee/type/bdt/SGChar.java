package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.precision.CharVarlena;
import org.lee.type.precision.TypePrecision;

public class SGChar extends AbstractBasicDataType {
    public SGChar() {
        super(TypeTag.CHAR, new CharVarlena());
    }

    public SGChar(int length) {
        super(TypeTag.CHAR, new CharVarlena(length));
    }
}
