package org.lee.type.precision;

import org.lee.base.Node;

public abstract class SizeofPrecision implements TypePrecision{
    protected final int sizeof;

    protected SizeofPrecision(int sizeof){
        this.sizeof = sizeof;
    }

    @Override
    public int getPrecision(int index) {
        return sizeof;
    }

    @Override
    public boolean isUnlimitedLength() {
        return sizeof == VARLENA_FLAG;
    }

    @Override
    public String toString() {
        return Node.EMPTY;
    }
}
