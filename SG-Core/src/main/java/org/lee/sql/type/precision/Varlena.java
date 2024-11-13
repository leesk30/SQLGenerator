package org.lee.sql.type.precision;

import org.lee.base.Node;

/**
 * 变长精度
 * */
public class Varlena implements TypePrecision {
    protected final int sizeof;
    public final static Varlena UNLIMITED_VARLENA_PRECISION = new Varlena(VARLENA_FLAG);

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

    @Override
    public String toString() {
        if(sizeof != VARLENA_FLAG){
            return Node.LP + sizeof + Node.RP;
        }else {
            return Node.EMPTY;
        }
    }
}
