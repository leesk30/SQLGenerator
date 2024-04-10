package org.lee.type.precision;

public abstract class BaseMultiSizeofPrecision implements TypePrecision {
    protected final int[] sizeofArray;

    protected BaseMultiSizeofPrecision(int[] sizeofArray) {
        this.sizeofArray = sizeofArray;
    }

    @Override
    public int getPrecisionSize() {
        return sizeofArray.length;
    }

    @Override
    public int getPrecision(int index) {
        if(index >= sizeofArray.length){
            throw new AssertionError(String.format("Out of precision description size. Access %d. Max: %d.", index, sizeofArray.length-1));
        }
        return sizeofArray[index];
    }
}
