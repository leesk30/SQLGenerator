package org.lee.type.precision;

public final class NumericVarlena implements TypePrecision{
    private final int head;
    private final int tail;

    public NumericVarlena(int head, int tail) {
        if(head != VARLENA_FLAG && head < tail){
            throw new AssertionError("Tail should be lower than head");
        }
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
}
