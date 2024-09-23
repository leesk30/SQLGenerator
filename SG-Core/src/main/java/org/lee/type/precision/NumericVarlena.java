package org.lee.type.precision;

import org.lee.exception.Assertion;
import org.lee.node.Node;

public final class NumericVarlena implements TypePrecision{
    private final int head;
    private final int tail;
    public final static NumericVarlena defaultPrecision = new NumericVarlena();

    public NumericVarlena(int head, int tail) {
        Assertion.requiredFalse(head != VARLENA_FLAG && head < tail);
        this.head = head;
        this.tail = tail;
    }

    public NumericVarlena(int head){
        this(head, DECIMAL_DEFAULT_TAIL);
    }

    public NumericVarlena(){
        this(VARLENA_FLAG, DECIMAL_DEFAULT_TAIL);
    }

    @Override
    public int getPrecision() {
        return MULTI_PRECISION_FLAG;
    }

    @Override
    public int getPrecision(int index) {
        if(index >= 1){
            throw new IndexOutOfBoundsException("Numeric precision index out of bounds. Got: " + index);
        }
        return 0;
    }

    @Override
    public boolean isUnlimitedLength() {
        return head == VARLENA_FLAG;
    }

    public int getIntDigitLength(){
        if(tail > 0){
            return head - tail;
        }
        return head;
    }

    public int getFloatDigitLength(){
        return tail;
    }

    @Override
    public String toString() {
        if(head > 0 && tail > 0){
            return Node.LP + head + Node.COMMA + tail + Node.RP;
        } else if (head > 0) {
            return Node.LP + head + Node.RP;
        }
        return Node.EMPTY;
    }
}
