package org.lee.type.precision;

/**
 * 变长精度
 * */
public class Varlena implements TypePrecision {
    private final int sizeof;
    public final static Varlena unlimited = new Varlena(VARLENA_FLAG);

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
