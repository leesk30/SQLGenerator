package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.precision.CharVarlena;

public class SGChar extends AbstractBasicDataType {
    public SGChar() {
        super(TypeTag.char_, new CharVarlena());
    }

    public SGChar(int length) {
        super(TypeTag.char_, new CharVarlena(length));
    }
}
