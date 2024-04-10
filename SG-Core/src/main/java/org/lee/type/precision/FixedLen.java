package org.lee.type.precision;

/**
 * 定长精度
 * */
public class FixedLen extends BaseSingeSizeofPrecision{
    private int sizeof;
    public FixedLen(int sizeof){
        super(sizeof);

    }

    @Override
    public int getPrecision(int index) {
        return sizeof;
    }

    @Override
    public boolean isUnlimitedLength() {
        return false;
    }
}
