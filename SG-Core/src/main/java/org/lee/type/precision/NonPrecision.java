package org.lee.type.precision;

import org.lee.node.Node;

public class NonPrecision implements TypePrecision{
    public static final NonPrecision instance = new NonPrecision();

    private NonPrecision(){
    }

    @Override
    public int getPrecision(int index) {
        return UNDEFINED_FLAG;
    }

    @Override
    public boolean isUnlimitedLength() {
        return true;
    }

    @Override
    public int getPrecisionSize() {
        return NON_PRECISION_SIZE;
    }

    @Override
    public String toString() {
        return Node.EMPTY;
    }
}
