package org.lee.type.precision;

/**
 * 变长精度
 * */
public class Varlena implements TypePrecision {
    private final int sizeof;

    public Varlena(int sizeof){
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
