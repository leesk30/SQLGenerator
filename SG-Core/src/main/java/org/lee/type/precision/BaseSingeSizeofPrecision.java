package org.lee.type.precision;

public abstract class BaseSingeSizeofPrecision implements TypePrecision{
    protected final int sizeof;

    protected BaseSingeSizeofPrecision(int sizeof){
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

}
