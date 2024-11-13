package org.lee.sql.type.precision;

import org.lee.base.Node;

/**
 * 定长精度
 * */
public class FixedLen extends SizeofPrecision {
    private int sizeof;

    public static final FixedLen sizeof4 = new FixedLen(4);
    public static final FixedLen sizeof6 = new FixedLen(6);
    public static final FixedLen sizeof8 = new FixedLen(8);

    public static final FixedLen sizeof1 = new FixedLen(1);

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

    @Override
    public String toString() {
        return Node.EMPTY;
    }
}
