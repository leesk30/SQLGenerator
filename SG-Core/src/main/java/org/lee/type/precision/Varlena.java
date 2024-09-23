package org.lee.type.precision;

import org.lee.node.Node;

/**
 * 变长精度
 * */
public class Varlena implements TypePrecision {
    protected final int sizeof;
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

    @Override
    public String toString() {
        if(sizeof != VARLENA_FLAG){
            return Node.LP + sizeof + Node.LP;
        }else {
            return Node.EMPTY;
        }
    }
}
