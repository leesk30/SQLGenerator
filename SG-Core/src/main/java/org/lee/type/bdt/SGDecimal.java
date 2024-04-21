package org.lee.type.bdt;

import org.lee.type.TypeTag;
import org.lee.type.base.AbstractBasicDataType;
import org.lee.type.precision.NumericVarlena;

public class SGDecimal extends AbstractBasicDataType {
    public SGDecimal() {
        super(TypeTag.decimal, NumericVarlena.defaultNumericVarlena);
    }

    public SGDecimal(int head) {
        super(TypeTag.decimal, new NumericVarlena(head));
    }

    public SGDecimal(int head, int tail) {
        super(TypeTag.decimal, new NumericVarlena(head, tail));
    }
}
